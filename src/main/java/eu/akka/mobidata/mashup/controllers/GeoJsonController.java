package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.enumeration.TargetAPIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.impl.GeoJsonService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nullable;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Handles the enrichment of a Geojson mobility data API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/geojson", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeoJsonController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeoJsonController.class);

    @Autowired
    GeoJsonService geoJsonService;

    @RequestMapping(value = "enrichGeoJsonApi", method = RequestMethod.GET)
    public @ResponseBody
    String enrichGeoJsonApi(@Parameter(description = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                            @Parameter(description = "Url of the target API to be enriched", example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String targetApiUrl,
                            @Parameter(description = "Url of the source API to be used for enrichment", example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String sourceApiUrl,
                            @Parameter(description = "source API format", schema = @Schema(description = "var 1",type = "string", allowableValues = {"GeoJson", "OSM", "GTFS"}, defaultValue = "OSM") ) APIFormatEnum apiFormat,
                            @Nullable @Parameter(description = "Target API authorization token") String targetToken,
                            @Nullable @Parameter(description = "Source API authorization token") String sourceToken) {

        targetApiUrl = URLDecoder.decode(targetApiUrl, StandardCharsets.UTF_8);
        sourceApiUrl = URLDecoder.decode(sourceApiUrl, StandardCharsets.UTF_8);

        // Get bus stops from open street map api
        String stops = geoJsonService.getGeoJsonBusStops(targetApiUrl, targetToken);

        if (stops == null) {
            throw new MobilityDataNotFoundException("No Bus stops found!");
        }

        return getEnrichedData(TargetAPIFormatEnum.GeoJson, apiFormat, sourceApiUrl, sourceToken, stops, enrichAttributes);
    }

}

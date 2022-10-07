package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.enumeration.TargetAPIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.impl.GeoJsonService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    String enrichGeoJsonApi(@ApiParam(value = "Attributes to be enriched on the target api, separated with commas", required = true, example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                            @ApiParam(value = "Url of the target API to be enriched", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String targetApiUrl,
                            @ApiParam(value = "Url of the source API to be used for enrichment", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String sourceApiUrl,
                            @ApiParam(value = "source API format", allowableValues = "GeoJson, OSM, GTFS", required = true, defaultValue = "OSM") APIFormatEnum apiFormat,
                            @ApiParam(value = "Target API authorization token") String targetToken,
                            @ApiParam(value = "Source API authorization token") String sourceToken) {

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

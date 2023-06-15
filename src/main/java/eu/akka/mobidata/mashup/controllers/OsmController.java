package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.enumeration.TargetAPIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Handles the enrichment of a OSM mobility data API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/osm", produces = MediaType.APPLICATION_JSON_VALUE)
public class OsmController extends BaseController {

    @RequestMapping(value = "enrichOsmApi", method = RequestMethod.GET)
    public @ResponseBody
    String enrichOsmApi(@Parameter(description = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                        @Parameter(description = "Url of the target API to be enriched", example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String targetApiUrl,
                        @Parameter(description = "Url of the source API to be used for enrichment", example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String sourceApiUrl,
                        @Parameter(description = "source API format", schema = @Schema(description = "var 1", type = "string", allowableValues = {"GeoJson", "OSM", "GTFS"}, defaultValue = "OSM")) APIFormatEnum apiFormat,
                        @Nullable @Parameter(description = "Target API authorization token") String targetToken,
                        @Nullable @Parameter(description = "Source API authorization token") String sourceToken) {

        targetApiUrl = URLDecoder.decode(targetApiUrl, StandardCharsets.UTF_8);
        sourceApiUrl = URLDecoder.decode(sourceApiUrl, StandardCharsets.UTF_8);

        // Get bus stops from open street map api
        String routes = osmService.getJsonFromOsmBusStops(targetApiUrl, targetToken);

        if (routes == null) {
            throw new MobilityDataNotFoundException("No Bus stops found!");
        }

        return getEnrichedData(TargetAPIFormatEnum.OSM, apiFormat, sourceApiUrl, sourceToken, routes, enrichAttributes);
    }

    /**
     * @param osmFile
     * @return geojson content
     */
    @RequestMapping(
            value = "convertOsmFileToGeoJson",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody
    String convertOsmFileToGeoJson(@RequestPart(value = "OSM file") MultipartFile osmFile) {
        try {
            return GeoJsonManager.convertOsmToGeoJson(osmFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert osm api to geoJson
     *
     * @param osmApiUrl osm api url
     * @return geoJson output
     */
    @RequestMapping(value = "convertOsmApiToGeoJson", method = RequestMethod.GET)
    public @ResponseBody
    String convertOsmApiToGeoJson(@Parameter(description = "Target api url (Only OSM api format is supported!)", example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String osmApiUrl,
                                  @Nullable @Parameter(description = "API authorization token") String token) {
        osmApiUrl = URLDecoder.decode(osmApiUrl, StandardCharsets.UTF_8);
        return osmService.getGeoJsonFromOsmBusStops(osmApiUrl, token);
    }
}

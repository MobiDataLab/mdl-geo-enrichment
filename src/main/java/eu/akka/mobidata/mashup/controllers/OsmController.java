package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.enumeration.TargetAPIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    String enrichOsmApi(@ApiParam(value = "Attributes to be enriched on the target api, separated with commas", required = true, example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                        @ApiParam(value = "Url of the target API to be enriched", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String targetApiUrl,
                        @ApiParam(value = "Url of the source API to be used for enrichment", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String sourceApiUrl,
                        @ApiParam(value = "source API format", allowableValues = "OSM, GeoJson, GTFS", required = true, defaultValue = "OSM") APIFormatEnum apiFormat,
                        @ApiParam(value = "Target API authorization token") String targetToken,
                        @ApiParam(value = "Source API authorization token") String sourceToken) {

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
    String convertOsmApiToGeoJson(@ApiParam(value = "Target api url (Only OSM api format is supported!)", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String osmApiUrl,
                                  @ApiParam(value = "API authorization token") String token) {
        osmApiUrl = URLDecoder.decode(osmApiUrl, StandardCharsets.UTF_8);
        return osmService.getGeoJsonFromOsmBusStops(osmApiUrl, token);
    }
}

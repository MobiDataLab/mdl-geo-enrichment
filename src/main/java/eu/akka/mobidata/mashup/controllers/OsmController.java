package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.services.interfaces.IOsmService;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import java.util.Base64;

/**
 * Handles the enrichment of a OSM mobility data API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/osm", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 429, message = "Too Many Requests")})
public class OsmController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsmController.class);

    @Autowired
    IOsmService osmService;

    @RequestMapping(value = "enrichOsmApi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String enrichOsmApi(@ApiParam(value = "Attributes to be enriched on the target api, separated with commas", required = true, example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                        @ApiParam(value = "Url of the target API to be enriched", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String targetApiUrl,
                        @ApiParam(value = "Url of the source API to be used for enrichment", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String sourceApiUrl,
                        @ApiParam(value = "source API format", allowableValues = "OSM, GeoJson, GTFS", required = true) APIFormatEnum apiFormat,
                        @ApiParam(value = "Target API authorization token") String targetToken,
                        @ApiParam(value = "Source API authorization token") String sourceToken) {

        throw new RuntimeException("Not yet implemented!");
    }

    /**
     * Convert data to geoJson
     *
     * @param osmData osm input Data
     * @return geoJson output
     */
    @RequestMapping(value = "convertOsmDataToGeoJson", method = RequestMethod.GET)
    public @ResponseBody
    String convertDataToGeoJson(@ApiParam(value = "Base 64 encoded OSM data content", required = true) String osmData) {
        return GeoJsonManager.convertOsmToGeoJson(new String(Base64.getDecoder().decode(osmData)));
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
                                  @ApiParam(value = "Source API authorization token") String sourceToken) {
        osmApiUrl = URLDecoder.decode(osmApiUrl, StandardCharsets.UTF_8);
        return osmService.getGeoJsonFromOsmBusStops(osmApiUrl, sourceToken);
    }
}

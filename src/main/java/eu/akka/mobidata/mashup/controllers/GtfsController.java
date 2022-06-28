package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles the enrichment of a GTFS mobility data API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/gtfs", produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {@ApiResponse(code = 429, message = "Too Many Requests")})
public class GtfsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GtfsController.class);

    @RequestMapping(value = "enrichGtfsApi", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String enrichGtfsApi(@ApiParam(value = "Attributes to be enriched on the target api, separated with commas", required = true, example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                         @ApiParam(value = "Url of the target API to be enriched", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String targetApiUrl,
                         @ApiParam(value = "Url of the source API to be used for enrichment", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String sourceApiUrl,
                         @ApiParam(value = "source API format", allowableValues = "GTFS, GeoJson, OSM", required = true) APIFormatEnum apiFormat,
                         @ApiParam(value = "Target API authorization token") String targetToken,
                         @ApiParam(value = "Source API authorization token") String sourceToken) {

        throw new RuntimeException("Not yet implemented!");
    }

    /**
     * Convert data to geoJson
     *
     * @param gtfsData gtfs input Data
     * @return geoJson output
     */
    @RequestMapping(value = "convertGtfsDataToGeoJson", method = RequestMethod.GET)
    public @ResponseBody
    String convertGtfsDataToGeoJson(@ApiParam(value = "Base 64 encoded GTFS data content", required = true) String gtfsData) {
        throw new RuntimeException("Not yet implemented!");
    }

    /**
     * Convert gtfs api to geoJson
     *
     * @param gtfsApiUrl gtfs api url
     * @return geoJson output
     */
    @RequestMapping(value = "convertGtfsApiToGeoJson", method = RequestMethod.GET)
    public @ResponseBody
    String convertGtfsApiToGeoJson(@ApiParam(value = "Target api url (Only GTFS api format is supported!)", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String gtfsApiUrl,
                                   @ApiParam(value = "Source API authorization token") String sourceToken) {
        throw new RuntimeException("Not yet implemented!");
    }

}

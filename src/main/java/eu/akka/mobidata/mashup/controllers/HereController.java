package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.enumeration.TargetAPIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.interfaces.IHereService;
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
 * Handles the mobility data / journeys in the Here REST API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/here", produces = MediaType.APPLICATION_JSON_VALUE)
public class HereController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HereController.class);

    @Autowired
    private IHereService hereService;

    @RequestMapping(value = "getRoutes", method = RequestMethod.GET)
    public @ResponseBody
    String getRoutes(@ApiParam(value = "Here API authorization key", example = "0PMpb1W_5iihYGu7UrBWsR8fI6Utopf52hFBKOwl7Xc") String apiKey,
                     @ApiParam(value = "Coordinates of starting point: latitude, longitude", required = true, example = "48.8345631,2.2433581") String fromCoordinates,
                     @ApiParam(value = "Coordinates of the arrival point: latitude, longitude", required = true, example = "48.8775033,2.4400646") String toCoordinates,
                     @ApiParam(value = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                     @ApiParam(value = "API format", allowableValues = "GeoJson, OSM, GTFS", required = true) APIFormatEnum apiFormat,
                     @ApiParam(value = "API full url", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String apiUrl,
                     @ApiParam(value = "Source API authorization token") String sourceToken) {

        apiUrl = URLDecoder.decode(apiUrl, StandardCharsets.UTF_8);

        // Get routes from here api
        String routes = hereService.findHereRoutes(apiKey, fromCoordinates, toCoordinates);

        if (routes == null) {
            throw new MobilityDataNotFoundException("No Here routes found!");
        }

        return getEnrichedData(TargetAPIFormatEnum.Here, apiFormat, apiUrl, sourceToken, routes, enrichAttributes);
    }

    @RequestMapping(value = "getNearStations", method = RequestMethod.GET)
    public @ResponseBody
    String getNearStations(@ApiParam(value = "Here API authorization key", example = "0PMpb1W_5iihYGu7UrBWsR8fI6Utopf52hFBKOwl7Xc") String apiKey,
                           @ApiParam(value = "Coordinates of the location: latitude, longitude", required = true, example = "48.876892,2.352623") String coordinates,
                           @ApiParam(value = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                           @ApiParam(value = "API format", allowableValues = "GeoJson, OSM, GTFS", required = true) APIFormatEnum apiFormat,
                           @ApiParam(value = "API full url", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway](48.856892, 2.332623,48.896892, 2.372623);node[railway](48.856892, 2.332623,48.896892, 2.372623);out%20meta;") String apiUrl,
                           @ApiParam(value = "Source API authorization token") String sourceToken) {

        apiUrl = URLDecoder.decode(apiUrl, StandardCharsets.UTF_8);

        // Get routes from here api
        String stations = hereService.findNearStations(apiKey, coordinates);

        if (stations == null) {
            throw new MobilityDataNotFoundException("No Here stations found!");
        }

        return getEnrichedData(TargetAPIFormatEnum.Here, apiFormat, apiUrl, sourceToken, stations, enrichAttributes);
    }
}

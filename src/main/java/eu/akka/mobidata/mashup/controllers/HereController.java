package eu.akka.mobidata.mashup.controllers;

import com.jayway.jsonpath.JsonPath;
import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.interfaces.IHereService;
import eu.akka.mobidata.mashup.services.interfaces.IOsmService;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import eu.akka.mobidata.mashup.util.OsmManager;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import net.minidev.json.JSONArray;
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
@ApiResponses(value = {@ApiResponse(code = 429, message = "Too Many Requests")})
public class HereController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HereController.class);

    @Autowired
    private IHereService hereService;

    @Autowired
    private IOsmService osmService;

    @RequestMapping(value = "getRoutes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getRoutes(@ApiParam(value = "Here API authorization key", example = "0PMpb1W_5iihYGu7UrBWsR8fI6Utopf52hFBKOwl7Xc") String apiKey,
                     @ApiParam(value = "Coordinates of starting point: latitude, longitude", required = true, example = "48.8345631,2.2433581") String fromCoordinates,
                     @ApiParam(value = "Coordinates of the arrival point: latitude, longitude", required = true, example = "48.8775033,2.4400646") String toCoordinates,
                     @ApiParam(value = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                     @ApiParam(value = "API format", allowableValues = "GeoJson, OSM", required = true) APIFormatEnum apiFormat,
                     @ApiParam(value = "API full url", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String apiUrl,
                     @ApiParam(value = "Source API authorization token") String sourceToken) {

        apiUrl = URLDecoder.decode(apiUrl, StandardCharsets.UTF_8);

        // Get routes from here api
        String routes = hereService.findHereRoutes(apiKey, fromCoordinates, toCoordinates);

        if (routes == null) {
            throw new MobilityDataNotFoundException("No Here routes found!");
        }

        if (APIFormatEnum.OSM.equals(apiFormat)) {
            // get bus stops for the same coordinates from osm
            String busStops = osmService.getGeoJsonBusStops(apiUrl, sourceToken);
            JSONArray osmElements = JsonPath.read(busStops, "$.elements");

            // aggregate and enrich here's bus stops from osm response
            OsmManager osmManager = new OsmManager(routes);
            return osmManager.aggregateBusStops(osmElements, enrichAttributes);
        } else if (APIFormatEnum.GeoJson.equals(apiFormat)) {
            // get bus stops for the same coordinates from osm
            String osmBusStops = osmService.getGeoJsonFromOsmBusStops(apiUrl, sourceToken);

            // load features from geo json response
            GeoJsonManager geoJsonManager = new GeoJsonManager(routes, osmBusStops);
            return geoJsonManager.aggregateHereBusStops(enrichAttributes);

        }
        return null;
    }

    @RequestMapping(value = "getNearStations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getNearStations(@ApiParam(value = "Here API authorization key", example = "0PMpb1W_5iihYGu7UrBWsR8fI6Utopf52hFBKOwl7Xc") String apiKey,
                           @ApiParam(value = "Coordinates of starting point: latitude, longitude", required = true, example = "48.876892,2.352623") String coordinates,
                           @ApiParam(value = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                           @ApiParam(value = "API format", allowableValues = "GeoJson, OSM", required = true) APIFormatEnum apiFormat,
                           @ApiParam(value = "API full url", required = true, example = "https://overpass.kumi.systems/api/interpreter?data=[out:json];node[highway](48.856892, 2.332623,48.896892, 2.372623);node[railway](48.856892, 2.332623,48.896892, 2.372623);out%20meta;") String apiUrl,
                           @ApiParam(value = "Source API authorization token") String sourceToken) {

        apiUrl = URLDecoder.decode(apiUrl, StandardCharsets.UTF_8);

        // Get routes from here api
        String stations = hereService.findNearStations(apiKey, coordinates);

        if (stations == null) {
            throw new MobilityDataNotFoundException("No Here stations found!");
        }

        if (APIFormatEnum.OSM.equals(apiFormat)) {
            // get bus stops for the same coordinates from osm
            String osmBusStops = osmService.getGeoJsonFromOsmBusStops(apiUrl, sourceToken);

            // load features from geo json response
            GeoJsonManager geoJsonManager = new GeoJsonManager(stations, osmBusStops);
            return geoJsonManager.aggregateHereBusStops(enrichAttributes);
        } else if (APIFormatEnum.GeoJson.equals(apiFormat)) {
            // get bus stops for the same coordinates from osm
            String geoJsonBusStops = osmService.getGeoJsonBusStops(apiUrl, sourceToken);

            // load features from geo json response
            GeoJsonManager geoJsonManager = new GeoJsonManager(stations, geoJsonBusStops);
            return geoJsonManager.aggregateHereBusStops(enrichAttributes);
        }
        return null;
    }
}

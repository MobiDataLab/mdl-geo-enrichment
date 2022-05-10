package eu.akka.mobidata.mashup.controllers;

import com.jayway.jsonpath.JsonPath;
import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.NavitiaService;
import eu.akka.mobidata.mashup.services.OsmService;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import eu.akka.mobidata.mashup.util.OsmManager;
import io.swagger.annotations.ApiParam;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Handles the mobility data / journeys in the REST API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/navitia", produces = MediaType.APPLICATION_JSON_VALUE)
public class JourneyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JourneyController.class);

    @Autowired
    private NavitiaService navitiaService;

    @Autowired
    private OsmService osmService;

    @RequestMapping(value = "getJourneys", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getJourneys(@RequestHeader Map<String, String> headers,
                       @ApiParam(value = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                       @ApiParam(value = "API format", required = true) APIFormatEnum apiFormat,
                       @ApiParam(value = "API full url", required = true, example = "https://www.overpass-api.de/api/interpreter?data=[out:json];node[highway=bus_stop](48.8345631,2.2433581,48.8775033,2.4400646);out%20meta;") String apiUrl,
                       @ApiParam(value = "Coordinates of starting point", required = true, example = "48.8345631,2.2433581") String fromCoordinates,
                       @ApiParam(value = "Coordinates of the arrival point", required = true, example = "48.8775033,2.4400646") String toCoordinates) {

        apiUrl = URLDecoder.decode(apiUrl, StandardCharsets.UTF_8);

        // Get journeys from Navitia
        String journeys = navitiaService.findJsonJourneys(fromCoordinates, toCoordinates);

        if (journeys == null) {
            throw new MobilityDataNotFoundException("No Navitia journeys found!");
        }

        if (APIFormatEnum.OSM.equals(apiFormat)) {
            // get bus stops for the same coordinates from osm
            String busStops = osmService.getJsonBusStops(apiUrl);
            JSONArray osmElements = JsonPath.read(busStops, "$.elements");

            // aggregate and enrich navitia's bus stops from osm response
            OsmManager osmManager = new OsmManager(journeys);
            return osmManager.aggregateBusStops(osmElements, enrichAttributes);
        } else if (APIFormatEnum.GeoJson.equals(apiFormat)) {
            // get bus stops for the same coordinates from osm
            String osmBusStops = osmService.getGeoJsonBusStops(apiUrl);

            // load features from geo json response
            GeoJsonManager geoJsonManager = new GeoJsonManager(journeys, osmBusStops);
            return geoJsonManager.aggregateBusStops(enrichAttributes);

        }
        return null;
    }


}

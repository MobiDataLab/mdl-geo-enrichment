package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.OsmService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles the mobility data resource in the REST API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping(value = "/api/v1/osm", produces = MediaType.APPLICATION_JSON_VALUE)
public class BusStopController {

    @Autowired
    private OsmService osmService;

    @RequestMapping(value = "getBusStops", method = RequestMethod.GET)
    public @ResponseBody
    String getBusStops(@ApiParam(value = "API full url") String apiUrl) {
        // Get bus stops from OpenStreetMap
        String busStops = osmService.getJsonBusStops(apiUrl);
        if (busStops == null) {
            throw new MobilityDataNotFoundException("No OpenStreetMap bus stops found!");
        }
        return busStops;
    }

}

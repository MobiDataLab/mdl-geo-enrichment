package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.domain.osm.OsmContainer;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.OsmService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/osm")
public class BusStopController {

    @Autowired
    private OsmService osmService;

    @RequestMapping(value = "getBusStops", method = RequestMethod.GET)
    public @ResponseBody
    OsmContainer getBusStops() {
        // Get bus stops from OpenStreetMap
        OsmContainer busStops = osmService.findBusStops();
        if (busStops == null) {
            throw new MobilityDataNotFoundException("No OpenStreetMap bus stops found!");
        }
        return busStops;
    }

}

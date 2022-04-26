package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.domain.navitia.NavitiaContainer;
import eu.akka.mobidata.mashup.domain.navitia.Section;
import eu.akka.mobidata.mashup.domain.osm.OsmContainer;
import eu.akka.mobidata.mashup.enumeration.APIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.NavitiaService;
import eu.akka.mobidata.mashup.services.OsmService;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import eu.akka.mobidata.mashup.util.OsmTools;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Handles the mobility data / journeys in the REST API.
 *
 * @author Mohamed.KARAMI
 */
@Controller
@RequestMapping("/api/v1/navitia")
public class JourneyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JourneyController.class);

    @Autowired
    private NavitiaService navitiaService;

    @Autowired
    private OsmService osmService;

    @RequestMapping(value = "getJourneys", method = RequestMethod.GET)
    public @ResponseBody
    NavitiaContainer getJourneys(@ApiParam(value = "Attributes to be enriched on the target api, separated with commas", example = "wheelchair, shelter, tactile_paving, bench, bin, lit") String enrichAttributes,
                                 @ApiParam(value = "API format", required = true) APIFormatEnum apiFormat,
                                 @ApiParam(value = "API full url", required = true, example = "https://www.overpass-api.de/api/interpreter?data=[out:json];node[highway=bus_stop](43.5690569,1.3951577,43.6283803,1.4803165);out%20meta;") String apiUrl,
                                 @ApiParam(value = "Coordinates of starting point", required = true, example = "1.3951577;43.5690569") String fromCoordinates,
                                 @ApiParam(value = "Coordinates of the arrival point", required = true, example = "1.4803165;43.6283803") String toCoordinates) {

        apiUrl = URLDecoder.decode(apiUrl, StandardCharsets.UTF_8);

        // Get journeys from Navitia
        NavitiaContainer journeys = navitiaService.findJourneys();

        if (journeys == null) {
            throw new MobilityDataNotFoundException("No Navitia journeys found!");
        }

        if (apiFormat.equals(APIFormatEnum.OSM)) {
            // get bus stops for the same coordinates from osm
            OsmContainer busStops = osmService.getOsmBusStops(apiUrl);

            // aggregate 'terminus' stops
            journeys.getTerminus().forEach(terminus -> OsmTools.aggregateBusStops(busStops, terminus, enrichAttributes));

            // aggregate 'from' & 'to' stops
            journeys.getJourneys().forEach(
                    journey -> journey.getSections().forEach(
                            section -> {
                                // aggregate 'from' stops
                                Optional.ofNullable(section)
                                        .map(Section::getFrom)
                                        .ifPresent(from -> OsmTools.aggregateBusStops(busStops, from, enrichAttributes));

                                // aggregate 'to' stops
                                Optional.ofNullable(section)
                                        .map(Section::getTo)
                                        .ifPresent(to -> OsmTools.aggregateBusStops(busStops, to, enrichAttributes));
                            }
                    )
            );
        } else {
            // get bus stops for the same coordinates from osm
            String busStops = osmService.getGeoJsonBusStops(apiUrl);

            // load features from geo json response
            GeoJsonManager geoJsonManager = new GeoJsonManager(busStops);

            // aggregate 'terminus' stops
            journeys.getTerminus().forEach(terminus -> geoJsonManager.aggregateBusStops(terminus, enrichAttributes));

            // aggregate 'from' & 'to' stops
            journeys.getJourneys().forEach(
                    journey -> journey.getSections().forEach(
                            section -> {
                                // aggregate 'from' stops
                                Optional.ofNullable(section)
                                        .map(Section::getFrom)
                                        .ifPresent(from -> geoJsonManager.aggregateBusStops(from, enrichAttributes));

                                // aggregate 'to' stops
                                Optional.ofNullable(section)
                                        .map(Section::getTo)
                                        .ifPresent(to -> geoJsonManager.aggregateBusStops(to, enrichAttributes));
                            }
                    )
            );
        }


        return journeys;
    }


}

package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.domain.navitia.NavitiaContainer;
import eu.akka.mobidata.mashup.domain.navitia.Section;
import eu.akka.mobidata.mashup.domain.navitia.StopContainer;
import eu.akka.mobidata.mashup.domain.osm.Element;
import eu.akka.mobidata.mashup.domain.osm.OsmContainer;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.NavitiaService;
import eu.akka.mobidata.mashup.services.OsmService;
import org.apache.commons.lang3.StringUtils;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
    NavitiaContainer getJourneys() {

        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();

        // Get journeys from Navitia
        NavitiaContainer journeys = navitiaService.findJourneys();

        if (journeys == null) {
            throw new MobilityDataNotFoundException();
        }

        // get bus stops for the same coordinates from osm
        OsmContainer busStops = osmService.findBusStops();
        List<Coordinate> busStopCoords = new ArrayList<>();
        busStops.getElements()
                .forEach(element -> busStopCoords.add(new Coordinate(element.getLon(), element.getLat())));

        // create a line of osm bus stops
        LineString lineString = geometryFactory.createLineString(busStopCoords.toArray(new Coordinate[busStopCoords.size()]));

        journeys.getJourneys().forEach(
                journey -> journey.getSections().forEach(
                        section -> {
                            // aggregate 'from' stops
                            Optional.ofNullable(section)
                                    .map(Section::getFrom)
                                    .ifPresent(from -> aggregateBusStops(geometryFactory, busStops, lineString, from));

                            // aggregate 'to' stops
                            Optional.ofNullable(section)
                                    .map(Section::getTo)
                                    .ifPresent(to -> aggregateBusStops(geometryFactory, busStops, lineString, to));
                        }
                )
        );

        return journeys;
    }

    private void aggregateBusStops(GeometryFactory geometryFactory, OsmContainer busStops, LineString lineString, StopContainer stopContainer) {
        Optional.ofNullable(stopContainer.getStopPoint())
                .ifPresent(stopPoint ->
                {
                    Coordinate coord = new Coordinate(
                            stopPoint.getStopArea().getCoord().getLon(),
                            stopPoint.getStopArea().getCoord().getLat());

                    Geometry geoNav = geometryFactory.createPoint(coord);

                    // get the nearest bus stops on the openstreetmap side
                    Coordinate[] nearestPoints = DistanceOp.nearestPoints(geoNav, lineString);

                    Optional<Element> eltBusStop = busStops.getElements().stream()
                            .filter(element ->
                                    Arrays.stream(nearestPoints)
                                            .anyMatch(nearPoint ->
                                                    // filter the closest points having less than 0.001D distance
                                                    geometryFactory.createPoint(nearPoint)
                                                            .distance(geometryFactory.createPoint(new Coordinate(element.getLon(), element.getLat()))) < 0.001D)
                            )
                            .max(Comparator.comparing(Element::getChangeset));

                    eltBusStop.ifPresent(element -> {
                        if (!StringUtils.isBlank(element.getTags().getWheelchair())) {
                            stopPoint.getAdditionalProperties().put("wheelchair", element.getTags().getWheelchair());
                            LOGGER.debug("bus stop : " + element.getTags().getName() + " v" + element.getVersion() + " is close to: " + stopPoint.getName());
                        }
                    });
                });

    }

}

package eu.akka.mobidata.mashup.controllers;

import eu.akka.mobidata.mashup.domain.navitia.*;
import eu.akka.mobidata.mashup.domain.osm.Element;
import eu.akka.mobidata.mashup.domain.osm.OsmContainer;
import eu.akka.mobidata.mashup.exceptions.MobilityDataNotFoundException;
import eu.akka.mobidata.mashup.services.NavitiaService;
import eu.akka.mobidata.mashup.services.OsmService;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.operation.distance.DistanceOp;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
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

    private static final GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
    List<SimpleFeature> simpleFeatureList = new ArrayList<>();

    @Autowired
    private NavitiaService navitiaService;

    @Autowired
    private OsmService osmService;

    @RequestMapping(value = "getJourneys", method = RequestMethod.GET)
    public @ResponseBody
    NavitiaContainer getJourneys(@ApiParam(value = "Attributes to be enriched on the target api", defaultValue = "wheelchair, shelter, tactile_paving, bench, bin, lit", required = false) String enrichAttributes) {
        NavitiaContainer journeys = null;
        try {
            // Get journeys from Navitia
            journeys = navitiaService.findJourneys();

            if (journeys == null) {
                throw new MobilityDataNotFoundException("No Navitia journeys found!");
            }

            // get bus stops for the same coordinates from osm
            String busStops = osmService.getGeoJsonBusStops();

            // load features from geo json response
            loadGeoJsonFeatures(busStops);

            // aggregate 'terminus' stops
            journeys.getTerminus().forEach(terminus -> aggregateBusStops(busStops, terminus, enrichAttributes));

            // aggregate 'from' & 'to' stops
            journeys.getJourneys().forEach(
                    journey -> journey.getSections().forEach(
                            section -> {
                                // aggregate 'from' stops
                                Optional.ofNullable(section)
                                        .map(Section::getFrom)
                                        .ifPresent(from -> aggregateBusStops(busStops, from, enrichAttributes));

                                // aggregate 'to' stops
                                Optional.ofNullable(section)
                                        .map(Section::getTo)
                                        .ifPresent(to -> aggregateBusStops(busStops, to, enrichAttributes));
                            }
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return journeys;
    }

    private void loadGeoJsonFeatures(String busStops) throws IOException {
        // read feature collection schema
        SimpleFeatureType featureType = new FeatureJSON().readFeatureCollectionSchema(busStops, false);
        FeatureJSON featureJSON = new FeatureJSON();
        featureJSON.setFeatureType(featureType);

        // load all features
        Optional<FeatureCollection> featureCollection = Optional.ofNullable(featureJSON.readFeatureCollection(busStops));

        // get all the features as list
        featureCollection.ifPresent(features -> {
            try (FeatureIterator featureIterator = features.features()) {
                while (featureIterator.hasNext()) {
                    simpleFeatureList.add((SimpleFeature) featureIterator.next());
                }
            }
            // close the iterator
        });
    }

    private void aggregateBusStops(String geoJsonBusStops, StopContainer stopContainer, String attributes) {
        try {
            // for the current navitia stop point we will look for the closest bugs tops on osm line
            Optional.ofNullable(stopContainer.getStopPoint())
                    .ifPresent(stopPoint ->
                    {
                        Coordinate coordinate = new Coordinate(
                                stopPoint.getStopArea().getCoord().getLon(),
                                stopPoint.getStopArea().getCoord().getLat());

                        // create point for the current bus stop
                        Geometry geoNav = geometryFactory.createPoint(coordinate);

                        enrichWithProperties(stopPoint, geoNav, simpleFeatureList, attributes);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void enrichWithProperties(StopPoint stopPoint, Geometry geoNav, List<SimpleFeature> simpleFeatureList, String attributes) {
        // get the closest feature/point to Navitia's bus stop
        simpleFeatureList.stream()
                .filter(feature ->
                        feature.getProperty("name").getValue() != null
                                && geoNav.getGeometryType().equalsIgnoreCase(((Geometry) feature.getDefaultGeometry()).getGeometryType())
                                && (stopPoint.getStopArea().getName().equalsIgnoreCase(feature.getProperty("name").getValue().toString())
                                || geoNav.isWithinDistance(((Geometry) feature.getDefaultGeometry()), 0.001D))
                )
                // get latest version/changeset only
                .max(Comparator.comparing(feature -> (Long) feature.getProperty("changeset").getValue()))
                .ifPresent(feature -> {
                    // remove white spaces from the attributes list then enrich the additional properties
                    Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                        Object propertyValue = feature.getProperty(attribute).getValue();
                        // set wheelchair information if exists
                        if (propertyValue != null) {
                            stopPoint.getAdditionalProperties().put(attribute, propertyValue);
                        }
                    });
                    LOGGER.debug("bus stop : " + feature.getProperty("name").getValue() + " v" + feature.getProperty("version").getValue() + " is close to: " + stopPoint.getStopArea().getName());
                });
    }

    // kept for compatibility purposes with osm format
    private void aggregateBusStops(OsmContainer busStops, StopContainer stopContainer) {
        // create a line of osm bus stops
        List<Coordinate> busStopCoords = new ArrayList<>();
        busStops.getElements()
                .forEach(element -> busStopCoords.add(new Coordinate(element.getLon(), element.getLat())));
        LineString lineString = geometryFactory.createLineString(busStopCoords.toArray(new Coordinate[busStopCoords.size()]));

        // for the current navitia stop point we will look for the closest bugs tops on osm line
        Optional.ofNullable(stopContainer.getStopPoint())
                .ifPresent(stopPoint ->
                {
                    Coordinate coord = new Coordinate(
                            stopPoint.getStopArea().getCoord().getLon(),
                            stopPoint.getStopArea().getCoord().getLat());

                    // create a point for the current bus stop
                    Geometry geoNav = geometryFactory.createPoint(coord);

                    // get the nearest bus stops from the openstreetmap side
                    Coordinate[] nearestPoints = DistanceOp.nearestPoints(geoNav, lineString);

                    Optional<Element> eltBusStop = busStops.getElements().stream()
                            .filter(element ->
                                    Arrays.stream(nearestPoints)
                                            .anyMatch(nearPoint ->
                                                    // filter the closest points having less than 0.001D distance
                                                    geometryFactory.createPoint(nearPoint)
                                                            .distance(geometryFactory.createPoint(new Coordinate(element.getLon(), element.getLat()))) < 0.001D)
                            )
                            // get the latest version / change set only
                            .max(Comparator.comparing(Element::getChangeset));

                    eltBusStop.ifPresent(element -> {
                        if (!StringUtils.isBlank(element.getTags().getWheelchair())) {
                            stopPoint.getAdditionalProperties().put("wheelchair", element.getTags().getWheelchair());
                            LOGGER.debug("bus stop : " + element.getTags().getName() + " v" + element.getVersion() + " is close to: " + stopPoint.getName());
                        }
                    });
                });
    }

    // aggregate terminus points
    private void aggregateBusStops(String geoJsonBusStops, Terminu terminu, String enrichAttributes) {
        try {
            // for the current navitia stop point we will look for the closest bugs tops on osm line
            Optional.ofNullable(terminu)
                    .ifPresent(terminus ->
                    {
                        Coordinate coordinate = new Coordinate(
                                terminus.getCoord().getLon(),
                                terminus.getCoord().getLat());

                        Geometry geoNav = geometryFactory.createPoint(coordinate);

                        enrichWithProperties(terminus, geoNav, simpleFeatureList, enrichAttributes);
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void enrichWithProperties(Terminu terminus, Geometry geoNav, List<SimpleFeature> simpleFeatureList, String attributes) {
        // get the closest feature/point to Navitia's bus stop
        simpleFeatureList.stream()
                .filter(feature ->
                        feature.getProperty("name").getValue() != null
                                && geoNav.getGeometryType().equalsIgnoreCase(((Geometry) feature.getDefaultGeometry()).getGeometryType())
                                && (terminus.getName().equalsIgnoreCase(feature.getProperty("name").getValue().toString())
                                || geoNav.isWithinDistance(((Geometry) feature.getDefaultGeometry()), 0.001D))
                )
                // get latest version/changeset only
                .max(Comparator.comparing(feature -> (Long) feature.getProperty("changeset").getValue()))
                .ifPresent(feature -> {
                    // remove white spaces from the attributes list then enrich the additional properties
                    Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                        Object propertyValue = feature.getProperty(attribute).getValue();
                        // set wheelchair information if exists
                        if (propertyValue != null) {
                            terminus.getAdditionalProperties().put(attribute, propertyValue);
                        }
                    });
                    LOGGER.debug("terminus : " + feature.getProperty("name").getValue() + " v" + feature.getProperty("version").getValue() + " is close to: " + terminus.getName());
                });
    }

}

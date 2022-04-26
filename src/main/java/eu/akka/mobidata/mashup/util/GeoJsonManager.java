package eu.akka.mobidata.mashup.util;

import eu.akka.mobidata.mashup.domain.navitia.StopContainer;
import eu.akka.mobidata.mashup.domain.navitia.StopPoint;
import eu.akka.mobidata.mashup.domain.navitia.Terminu;
import eu.akka.mobidata.mashup.exceptions.BadRequestException;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geojson.feature.FeatureJSON;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Handles geoJson parsing
 *
 * @author Mohamed.KARAMI
 */
public class GeoJsonManager {
    public GeoJsonManager(String geoJsonString) {
        try {
            loadGeoJsonFeatures(geoJsonString);
        } catch (IOException e) {
            throw new BadRequestException("Malformed GeoJson response!");
        }
    }

    private List<SimpleFeature> simpleFeatureList = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(GeoJsonManager.class);

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
        });
    }


    public void enrichWithProperties(StopPoint stopPoint, Geometry geoNav, String attributes) {
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

    public void aggregateBusStops(StopContainer stopContainer, String attributes) {
        try {
            // for the current navitia stop point we will look for the closest bugs tops on osm line
            Optional.ofNullable(stopContainer.getStopPoint())
                    .ifPresent(stopPoint ->
                    {
                        Coordinate coordinate = new Coordinate(
                                stopPoint.getStopArea().getCoord().getLon(),
                                stopPoint.getStopArea().getCoord().getLat());

                        // create point for the current bus stop
                        Geometry geoNav = GeometryTools.geometryFactory.createPoint(coordinate);

                        enrichWithProperties(stopPoint, geoNav, attributes);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // aggregate terminus points
    public void aggregateBusStops(Terminu terminu, String enrichAttributes) {
        try {
            // for the current navitia stop point we will look for the closest bugs tops on osm line
            Optional.ofNullable(terminu)
                    .ifPresent(terminus ->
                    {
                        Coordinate coordinate = new Coordinate(
                                terminus.getCoord().getLon(),
                                terminus.getCoord().getLat());

                        Geometry geoNav = GeometryTools.geometryFactory.createPoint(coordinate);

                        enrichWithProperties(terminus, geoNav, enrichAttributes);
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void enrichWithProperties(Terminu terminus, Geometry geoNav, String attributes) {
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

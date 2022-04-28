package eu.akka.mobidata.mashup.util;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import eu.akka.mobidata.mashup.exceptions.BadRequestException;
import org.apache.commons.io.IOUtils;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geojson.feature.FeatureJSON;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Handles geoJson parsing
 *
 * @author Mohamed.KARAMI
 */
public class GeoJsonManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeoJsonManager.class);
    private List<SimpleFeature> simpleFeatureList;
    private DocumentContext targetApiContext;

    public GeoJsonManager(String targetApiResponse, String geoJsonString) {
        try {
            // load navitia's response to the parser
            this.targetApiContext = JsonPath.parse(targetApiResponse);
            // parse and load osm's response
            loadGeoJsonFeatures(geoJsonString);
        } catch (IOException e) {
            throw new BadRequestException("Malformed GeoJson response!");
        }
    }


    private String convertOsmToGeoJson(String osmResponse) {
        String osmGeoJson = null;

        try {
            //convert osm to geojson
            Runtime runtime = Runtime.getRuntime();

            // write osm response to temporary file
            Path tempFile = Files.createTempFile(null, null);
            Files.write(tempFile, osmResponse.getBytes(StandardCharsets.UTF_8));

            // get npm path
            Process process = runtime.exec(Constants.NPM_GET_PATH);
            String npmPath = IOUtils.toString(process.getInputStream(), Charset.defaultCharset()).replace("\n", "");

            // convert osm to geojson
            String[] command = {npmPath + File.separator + Constants.OSM_TO_GEOJSON, tempFile.toString()};
            process = runtime.exec(command);
            osmGeoJson = IOUtils.toString(process.getInputStream(), Charset.defaultCharset());

            Files.delete(tempFile);
        } catch (IOException e) {
            LOGGER.error("Please make sure nodejs and osmtogeojson module are installed!");
            LOGGER.error(e.getMessage());
        }
        return osmGeoJson;
    }

    private void loadGeoJsonFeatures(String busStops) throws IOException {
        // read feature collection schema
        FeatureJSON featureJSON = new FeatureJSON();
        SimpleFeatureType featureType = featureJSON.readFeatureCollectionSchema(busStops, false);

        if (featureType.getAttributeCount() < 1) {
            busStops = convertOsmToGeoJson(busStops);
            featureType = featureJSON.readFeatureCollectionSchema(busStops, false);

            if (featureType.getAttributeCount() < 1) {
                throw new BadRequestException("Malformed GeoJson response!");
            }
        }
        featureJSON.setFeatureType(featureType);

        // load all features
        Optional<FeatureCollection> featureCollection = Optional.ofNullable(featureJSON.readFeatureCollection(busStops));
        simpleFeatureList = new ArrayList<>();

        // get all the features as list
        featureCollection.ifPresent(features -> {
            try (FeatureIterator featureIterator = features.features()) {
                while (featureIterator.hasNext()) {
                    simpleFeatureList.add((SimpleFeature) featureIterator.next());
                }
            }
        });
    }

    public String aggregateBusStops(String attributes) {

        try {
            List<LinkedHashMap> stopAreas = this.targetApiContext.read("$..stop_area");
            // for the current navitia stop point we will look for the closest bugs tops on osm line
            stopAreas.forEach(stopArea ->
            {
                LinkedHashMap coords = (LinkedHashMap) stopArea.get("coord");
                Coordinate coordinate = new Coordinate(
                        Double.parseDouble(coords.get("lon").toString()),
                        Double.parseDouble(coords.get("lat").toString()));

                Geometry geoNav = GeometryTools.geometryFactory.createPoint(coordinate);

                enrichWithProperties(attributes, stopArea, geoNav);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        return targetApiContext.jsonString();
    }

    public void enrichWithProperties(String attributes, LinkedHashMap stopArea, Geometry geoNav) {
        // get the closest feature/point to Navitia's bus stop
        simpleFeatureList.stream()
                .filter(feature ->
                        feature.getProperty("name").getValue() != null
                                && geoNav.getGeometryType().equalsIgnoreCase(((Geometry) feature.getDefaultGeometry()).getGeometryType())
                                && (stopArea.get("name").equals(feature.getProperty("name").getValue())
                                || geoNav.isWithinDistance(((Geometry) feature.getDefaultGeometry()), 0.001D))
                )
                // get latest version/changeset only
                .max(Comparator.comparing(feature -> (Long) feature.getProperty("changeset").getValue()))
                .ifPresent(feature -> {
                    // remove white spaces from the attributes list then enrich the additional properties
                    Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                        Object propertyValue = feature.getProperty(attribute).getValue();
                        // set attributes information if exists
                        if (propertyValue != null) {
                            Object stopId = stopArea.get("id");
                            // add new attribute to navitia with the same name as osm
                            this.targetApiContext.put("$..stop_area[?(@.id=='" + stopId + "')]", attribute, propertyValue);
                        }
                    });
                    LOGGER.debug("Bus stop : " + feature.getProperty("name").getValue() + " v" + feature.getProperty("version").getValue() + " is close to: " + stopArea.get("name"));
                });
    }
}

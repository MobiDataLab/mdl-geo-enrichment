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
import org.opengis.feature.Property;
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


    public static String convertOsmToGeoJson(String osmResponse) {
        String osmGeoJson = null;

        try {
            //convert osm to geojson
            Runtime runtime = Runtime.getRuntime();

            // write osm response to temporary file
            Path tempFile = Files.createTempFile(null, null);
            Files.write(tempFile, osmResponse.getBytes(StandardCharsets.UTF_8));

            // get npm path
            Process process;
            String[] command = {Constants.OSM_TO_GEOJSON, tempFile.toString()};

            if (Utils.isWindows()) {
                process = runtime.exec(Constants.NPM_GET_WIN_PATH);
                String npmPath = IOUtils.toString(process.getInputStream(), Charset.defaultCharset()).replace("\n", "");

                // convert osm to geojson
                command = new String[]{npmPath + File.separator + Constants.OSM_TO_GEOJSON, tempFile.toString()};
            }

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
            // try to convert to geojson
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

    public String aggregateNavitiaBusStops(String attributes) {
        List<LinkedHashMap> stopPoints = this.targetApiContext.read("$..stop_point");

        // for the current navitia stop point we will look for the closest bugs tops on osm line
        stopPoints.parallelStream().forEach(stopPointNavitia ->
        {
            // create empty enriched properties array
            stopPointNavitia.put("enriched_properties", new LinkedHashMap());

            LinkedHashMap coords = (LinkedHashMap) ((LinkedHashMap) stopPointNavitia.get("address")).get("coord");
            Coordinate coordinate = new Coordinate(
                    Double.parseDouble(coords.get("lon").toString()),
                    Double.parseDouble(coords.get("lat").toString()));

            Geometry geoNav = GeometryTools.geometryFactory.createPoint(coordinate);

            enrichNavitiaWithProperties(attributes, stopPointNavitia, geoNav);
        });

        return targetApiContext.jsonString();
    }

    public void enrichNavitiaWithProperties(String attributes, LinkedHashMap stopPointNavitia, Geometry geoNavitia) {
        // get the closest feature/point to Navitia's bus stop and enrich it
        Object stopId = stopPointNavitia.get("id");
        if (stopId != null) {
            simpleFeatureList.parallelStream()
                    .filter(feature ->
                            feature.getProperty("name").getValue() != null
                                    && geoNavitia.getGeometryType().equalsIgnoreCase(((Geometry) feature.getDefaultGeometry()).getGeometryType())
                                    && (stopPointNavitia.get("name").equals(feature.getProperty("name").getValue())
                                    || geoNavitia.isWithinDistance(((Geometry) feature.getDefaultGeometry()), 0.001D))
                    )
                    // get latest version/changeset only
                    .max(Comparator.comparing(feature -> (Long) feature.getProperty("changeset").getValue()))
                    .ifPresent(feature -> {
                        // remove white spaces from the attributes list then enrich the additional properties
                        Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                            Property property = feature.getProperty(attribute);
                            // add new attribute to navitia's bus stop equipments
                            setAttribute(stopPointNavitia, property);
                        });
                        LOGGER.debug("Bus stop : " + feature.getProperty("name").getValue() + " v" + feature.getProperty("version").getValue() + " is close to: " + stopPointNavitia.get("name"));
                    });
        }
    }

    public String aggregateHereBusStops(String attributes) {
        List<LinkedHashMap> places = this.targetApiContext.read("$..place");

        // for the current navitia stop point we will look for the closest bugs tops on osm line
        places.parallelStream().forEach(place ->
        {
            // create empty enriched properties array
            place.put("enriched_properties", new LinkedHashMap());

            LinkedHashMap location = (LinkedHashMap) place.get("location");
            Coordinate coordinate = new Coordinate(
                    Double.parseDouble(location.get("lng").toString()),
                    Double.parseDouble(location.get("lat").toString()));

            Geometry geoHere = GeometryTools.geometryFactory.createPoint(coordinate);

            enrichHereWithProperties(attributes, place, geoHere);
        });

        return targetApiContext.jsonString();
    }

    public void enrichHereWithProperties(String attributes, LinkedHashMap place, Geometry geoHere) {
        // get the closest feature/point to Navitia's bus stop and enrich it
        Object placeName = place.get("name");
        if (placeName != null) {
            simpleFeatureList.parallelStream()
                    .filter(feature ->
                            feature.getProperty("name").getValue() != null
                                    && geoHere.getGeometryType().equalsIgnoreCase(((Geometry) feature.getDefaultGeometry()).getGeometryType())
                                    && (place.get("name").equals(feature.getProperty("name").getValue())
                                    || geoHere.isWithinDistance(((Geometry) feature.getDefaultGeometry()), 0.001D))
                    )
                    // get latest version/changeset only
                    .max(Comparator.comparing(feature -> (Long) feature.getProperty("changeset").getValue()))
                    .ifPresent(feature -> {
                        // remove white spaces from the attributes list then enrich the additional properties
                        Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                            Property property = feature.getProperty(attribute);
                            // add new attribute to navitia's bus stop equipments
                            setAttribute(place, property);
                        });
                        LOGGER.debug("Bus stop : " + feature.getProperty("name").getValue() + " v" + feature.getProperty("version").getValue() + " is close to: " + placeName);
                    });
        }
    }

    private void setAttribute(LinkedHashMap stopPointNavitia, Property property) {
        LinkedHashMap enriched_properties = (LinkedHashMap) stopPointNavitia.get("enriched_properties");
        if (property != null && property.getValue() != null) {
            enriched_properties.put(property.getName(), property.getValue());
        }
    }
}

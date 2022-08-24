package eu.akka.mobidata.mashup.util;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import eu.akka.mobidata.mashup.enumeration.TargetAPIFormatEnum;
import eu.akka.mobidata.mashup.exceptions.BadRequestException;
import net.minidev.json.JSONArray;
import org.apache.commons.io.FileUtils;
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
import java.nio.file.Paths;
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


    public static String convertOsmToGeoJson(byte[] osmFile) {
        String osmGeoJson = null;

        if (osmFile == null) {
            throw new RuntimeException("OSM response is empty!");
        }

        try {
            //convert osm to geojson
            Runtime runtime = Runtime.getRuntime();

            // write osm response to a temporary file
            Path tempFile = Files.createTempFile(null, null);
            Files.write(tempFile, osmFile);

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

            Files.deleteIfExists(tempFile);
        } catch (IOException e) {
            LOGGER.error("Please make sure nodejs and osmtogeojson module are installed!");
            LOGGER.error(e.getMessage());
        }
        return osmGeoJson;
    }

    public static String convertGtfsToGeoJson(byte[] gtfsResponse) {
        String gtfsGeoJson = null;
        String myAgencyKey = "MyAgency_" + UUID.randomUUID();

        if (gtfsResponse == null) {
            throw new RuntimeException("GTFS response is empty!");
        }

        try {
            // write gtfs response to a temporary file
            Path tempGTFSFile = Files.createTempFile(null, ".zip");
            Files.write(tempGTFSFile, gtfsResponse);

            // write gtfs config
            String myAgencyConfig = Constants.GTFS_CONFIG_JSON.replace("my_agency_key", myAgencyKey);
            myAgencyConfig = myAgencyConfig.replace("gtfs_path", tempGTFSFile.getFileName().toString());
            Path tempGTFSConfigFile = Files.createTempFile(null, ".json");
            Files.write(tempGTFSConfigFile, myAgencyConfig.getBytes(StandardCharsets.UTF_8));

            Runtime runtime = Runtime.getRuntime();
            Process process;

            // command to convert gtfs to geojson
            String[] command = {Constants.GTFS_TO_GEOJSON, "--configPath", tempGTFSConfigFile.toString()};

            if (Utils.isWindows()) {
                // get npm path
                process = runtime.exec(Constants.NPM_GET_WIN_PATH);
                String npmPath = IOUtils.toString(process.getInputStream(), Charset.defaultCharset()).replace("\n", "");

                command = new String[]{npmPath + File.separator + Constants.GTFS_TO_GEOJSON, "--configPath", tempGTFSConfigFile.toString()};
            }

            // we use ProcessBuilder to set a custom working directory
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(tempGTFSConfigFile.getParent().toFile());
            processBuilder.command(command);
            process = processBuilder.start();
            LOGGER.debug(IOUtils.toString(process.getInputStream(), Charset.defaultCharset()));

            // get generated geojson file
            Path geojsonFile = Paths.get(tempGTFSConfigFile.getParent().toString(), "geojson", myAgencyKey, myAgencyKey + ".geojson");
            gtfsGeoJson = Files.readString(geojsonFile, Charset.defaultCharset());

            // delete temporary files
            Files.deleteIfExists(tempGTFSFile);
            Files.deleteIfExists(tempGTFSConfigFile);
            FileUtils.deleteDirectory(geojsonFile.getParent().toFile());
        } catch (IOException e) {
            LOGGER.error("Please make sure nodejs and gtfs-to-geojson module are installed!");
            LOGGER.error(e.getMessage());
        }
        return gtfsGeoJson;
    }

    private void loadGeoJsonFeatures(String busStops) throws IOException {
        // read feature collection schema
        FeatureJSON featureJSON = new FeatureJSON();
        SimpleFeatureType featureType = featureJSON.readFeatureCollectionSchema(busStops, false);

        if (featureType.getAttributeCount() < 1) {
            // try to convert to geojson
            busStops = convertOsmToGeoJson(busStops.getBytes(StandardCharsets.UTF_8));
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
        if (stopPoints.isEmpty()) {
            stopPoints = this.targetApiContext.read("$..stop_area");
        }
        // for the current navitia stop point we will look for the closest bugs tops on osm line
        stopPoints.parallelStream().forEach(stopPointNavitia ->
        {
            // create empty enriched properties array
            stopPointNavitia.put("enriched_properties", new LinkedHashMap());

            LinkedHashMap coords = (stopPointNavitia.get("address") == null ? (LinkedHashMap) stopPointNavitia.get("coord") : (LinkedHashMap) ((LinkedHashMap) stopPointNavitia.get("address")).get("coord"));
            Coordinate coordinate = new Coordinate(
                    Double.parseDouble(coords.get("lon").toString()),
                    Double.parseDouble(coords.get("lat").toString()));

            Geometry geometry = GeometryTools.geometryFactory.createPoint(coordinate);

            enrichNavitiaWithProperties(attributes, stopPointNavitia, geometry);
        });

        return targetApiContext.jsonString();
    }

    public void enrichNavitiaWithProperties(String attributes, LinkedHashMap stopPointNavitia, Geometry geoNavitia) {
        // get the closest feature/point to Navitia's bus stop and enrich it
        Object placeName = stopPointNavitia.get("id");
        enrichPoint(placeName, geoNavitia, attributes, stopPointNavitia, stopPointNavitia.get("name"), TargetAPIFormatEnum.Navitia);
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

            Geometry geometry = GeometryTools.geometryFactory.createPoint(coordinate);

            enrichHereWithProperties(attributes, place, geometry);
        });

        return targetApiContext.jsonString();
    }

    public void enrichHereWithProperties(String attributes, LinkedHashMap place, Geometry geoHere) {
        // get the closest feature/point to Navitia's bus stop and enrich it
        Object placeName = place.get("name");
        enrichPoint(placeName, geoHere, attributes, place, placeName, TargetAPIFormatEnum.Here);
    }

    public String aggregateOsmBusStops(String attributes) {
        JSONArray elements = this.targetApiContext.read("$..elements");

        ((List<LinkedHashMap>) elements.get(0)).parallelStream().forEach(element ->
        {
            // create empty enriched properties array
            element.put("enriched_properties", new LinkedHashMap());

            Coordinate coordinate = new Coordinate(
                    Double.parseDouble(element.get("lon").toString()),
                    Double.parseDouble(element.get("lat").toString()));

            Geometry geometry = GeometryTools.geometryFactory.createPoint(coordinate);

            enrichOsmWithProperties(attributes, element, geometry);
        });

        return targetApiContext.jsonString();
    }

    public void enrichOsmWithProperties(String attributes, LinkedHashMap element, Geometry geoHere) {
        LinkedHashMap tags = (LinkedHashMap) element.get("tags");
        Object placeName = tags.get("name");
        enrichPoint(placeName, geoHere, attributes, element, placeName, TargetAPIFormatEnum.OSM);
    }

    public String aggregateGeoJsonBusStops(String attributes) {
        List<LinkedHashMap> features = this.targetApiContext.read("$..features");

        ((List<LinkedHashMap>) features.get(0)).parallelStream().forEach(feature ->
        {
            // create empty enriched properties array
            ((LinkedHashMap) feature.get("properties")).put("enriched_properties", new LinkedHashMap());

            JSONArray coordinates = (JSONArray) ((LinkedHashMap) feature.get("geometry")).get("coordinates");
            Coordinate coordinate = new Coordinate(
                    Double.parseDouble(coordinates.get(0).toString()),
                    Double.parseDouble(coordinates.get(1).toString()));

            Geometry geometry = GeometryTools.geometryFactory.createPoint(coordinate);

            enrichGeoJsonWithProperties(attributes, feature, geometry);
        });

        return targetApiContext.jsonString();
    }

    public void enrichGeoJsonWithProperties(String attributes, LinkedHashMap stopPoint, Geometry geoHere) {
        // get the closest feature/point to Navitia's bus stop and enrich it
        Object placeName = ((LinkedHashMap) stopPoint.get("properties")).get("name");
        enrichPoint(placeName, geoHere, attributes, stopPoint, placeName, TargetAPIFormatEnum.GeoJson);
    }

    private void enrichPoint(Object placeName, Geometry geoNavitia, String attributes, LinkedHashMap stopPointNavitia, Object stopPointNavitia1, TargetAPIFormatEnum apiFormatEnum) {
        if (placeName != null) {
            simpleFeatureList.parallelStream()
                    .filter(feature ->
                            feature.getProperty("name").getValue() != null
                                    && geoNavitia.getGeometryType().equalsIgnoreCase(((Geometry) feature.getDefaultGeometry()).getGeometryType())
                                    && (placeName.equals(feature.getProperty("name").getValue())
                                    || geoNavitia.isWithinDistance(((Geometry) feature.getDefaultGeometry()), 0.001D))
                    )
                    // get latest version/changeset only
                    .max(Comparator.comparing(feature -> (Long) feature.getProperty("changeset").getValue()))
                    .ifPresent(feature -> {
                        // remove white spaces from the attributes list then enrich the additional properties
                        Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                            Property property = feature.getProperty(attribute);
                            // add new attribute to navitia's bus stop equipments
                            if (property != null && property.getValue() != null) {
                                setAttribute(stopPointNavitia, property, apiFormatEnum);
                            }
                        });
                        LOGGER.debug("Bus stop : " + feature.getProperty("name").getValue() + " v" + feature.getProperty("version").getValue() + " is close to: " + stopPointNavitia1);
                    });
        }
    }

    private void setAttribute(LinkedHashMap stopPoint, Property property, TargetAPIFormatEnum apiFormatEnum) {
        LinkedHashMap enriched_properties;
        switch (apiFormatEnum) {
            case GeoJson ->
                    enriched_properties = (LinkedHashMap) ((LinkedHashMap) stopPoint.get("properties")).get("enriched_properties");
            case OSM ->
                    enriched_properties = (LinkedHashMap) ((LinkedHashMap) stopPoint.get("tags")).get("enriched_properties");
            default -> enriched_properties = (LinkedHashMap) stopPoint.get("enriched_properties");
        }
        enriched_properties.put(property.getName(), property.getValue());
    }
}

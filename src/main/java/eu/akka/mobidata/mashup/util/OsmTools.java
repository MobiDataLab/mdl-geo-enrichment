package eu.akka.mobidata.mashup.util;

import eu.akka.mobidata.mashup.domain.navitia.StopContainer;
import eu.akka.mobidata.mashup.domain.navitia.Terminu;
import net.minidev.json.JSONArray;
import org.apache.commons.io.IOUtils;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Handles the Openstreetmap format.
 *
 * @author Mohamed.KARAMI
 */
public class OsmTools {
    private static final Logger LOGGER = LoggerFactory.getLogger(OsmTools.class);

    public static String convertOsmToGeoJson(String navObject) throws IOException {
        Path tempFile = null;
        try {
            //convert osm to geojson
            Runtime runtime = Runtime.getRuntime();

            // write osm response to temporary file
            tempFile = Files.createTempFile(null, null);
            Files.write(tempFile, navObject.getBytes(StandardCharsets.UTF_8));

            // get npm path
            Process process = runtime.exec(Constants.NPM_GET_PATH);
            String npmPath = IOUtils.toString(process.getInputStream(), Charset.defaultCharset()).replace("\n", "");

            // convert osm to geojson
            String[] command = {npmPath + File.separator + Constants.OSM_TO_GEOJSON, tempFile.toString()};
            process = runtime.exec(command);
            return IOUtils.toString(process.getInputStream(), Charset.defaultCharset());
        } catch (IOException e) {
            LOGGER.error("Please make sure nodejs and osmtogeojson module are installed!");
            LOGGER.error(e.getMessage());
        } finally {
            // remove temporary file
            if (tempFile != null) {
                Files.delete(tempFile);
            }
        }
        return null;
    }

    public static void aggregateBusStops(JSONArray busStops, StopContainer stopContainer, String attributes) {

        try {
            // for the current navitia stop point we will look for the closest bugs tops on osm line
            Optional.ofNullable(stopContainer.getStopPoint())
                    .ifPresent(navStopPoint ->
                    {
                        Coordinate coordinate = new Coordinate(
                                navStopPoint.getCoord().getLon(),
                                navStopPoint.getCoord().getLat());

                        Geometry geoNav = GeometryTools.geometryFactory.createPoint(coordinate);

                        List<LinkedHashMap> elements = busStops.stream()
                                .map(element -> (LinkedHashMap) element)
                                .collect(Collectors.toList());

                        enrichPoint(attributes, stopContainer, geoNav, elements);
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void aggregateBusStops(JSONArray busStops, Terminu terminu, String attributes) {

        try {
            // for the current navitia stop point we will look for the closest bugs tops on osm line
            Optional.ofNullable(terminu)
                    .ifPresent(terminus ->
                    {
                        Coordinate coordinate = new Coordinate(
                                terminus.getCoord().getLon(),
                                terminus.getCoord().getLat());

                        Geometry geoNav = GeometryTools.geometryFactory.createPoint(coordinate);

                        List<LinkedHashMap> elements = busStops.stream()
                                .map(element -> (LinkedHashMap) element)
                                .collect(Collectors.toList());

                        enrichPoint(attributes, terminus, geoNav, elements);
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void enrichPoint(String attributes, StopContainer stopContainer, Geometry geoNav, List<LinkedHashMap> elements) {
        elements.stream()
                .filter(element ->
                        {
                            Coordinate coordOsm = new Coordinate(
                                    Double.parseDouble(element.get("lon").toString()),
                                    Double.parseDouble(element.get("lat").toString()));

                            Geometry geoOsm = GeometryTools.geometryFactory.createPoint(coordOsm);
                            Object name = ((LinkedHashMap<?, ?>) element.get("tags")).get("name");
                            return name != null
                                    && element.get("type").equals("node") // point type
                                    && (stopContainer.getName().equals(name)
                                    || geoNav.isWithinDistance(geoOsm, 0.001D));
                        }
                )
                // get latest version/changeset only
                .max(Comparator.comparing(element -> (Integer) element.get("changeset")))
                .ifPresent(element -> {
                    LinkedHashMap tags = (LinkedHashMap) element.get("tags");

                    // remove white spaces from the attributes list then enrich the additional properties
                    Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                        Object propertyValue = tags.get(attribute);
                        // set wheelchair information if exists
                        if (propertyValue != null) {
                            stopContainer.getAdditionalProperties().put(attribute, propertyValue);
                        }
                    });
                    Object name = tags.get("name");
                    LOGGER.debug("terminus : " + name + " v" + element.get("version") + " is close to: " + stopContainer.getName());
                });
    }

    private static void enrichPoint(String attributes, Terminu terminus, Geometry geoNav, List<LinkedHashMap> elements) {
        elements.stream()
                .filter(element ->
                        {
                            Coordinate coordOsm = new Coordinate(
                                    Double.parseDouble(element.get("lon").toString()),
                                    Double.parseDouble(element.get("lat").toString()));

                            Geometry geoOsm = GeometryTools.geometryFactory.createPoint(coordOsm);
                            Object name = ((LinkedHashMap<?, ?>) element.get("tags")).get("name");
                            return name != null
                                    && element.get("type").equals("node") // point type
                                    && (terminus.getName().equals(name)
                                    || geoNav.isWithinDistance(geoOsm, 0.001D));
                        }
                )
                // get latest version/changeset only
                .max(Comparator.comparing(element -> (Integer) element.get("changeset")))
                .ifPresent(element -> {
                    LinkedHashMap tags = (LinkedHashMap) element.get("tags");

                    // remove white spaces from the attributes list then enrich the additional properties
                    Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                        Object propertyValue = tags.get(attribute);
                        // set wheelchair information if exists
                        if (propertyValue != null) {
                            terminus.getAdditionalProperties().put(attribute, propertyValue);
                        }
                    });
                    Object name = tags.get("name");
                    LOGGER.debug("terminus : " + name + " v" + element.get("version") + " is close to: " + terminus.getName());
                });
    }

}

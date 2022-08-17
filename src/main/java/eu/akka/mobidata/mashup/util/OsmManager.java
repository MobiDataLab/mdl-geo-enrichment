package eu.akka.mobidata.mashup.util;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles the Openstreetmap format.
 *
 * @author Mohamed.KARAMI
 */
public class OsmManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(OsmManager.class);

    private DocumentContext targetApiContext;

    public OsmManager(String targetApiResponse) {
        this.targetApiContext = JsonPath.parse(targetApiResponse);
    }

    //@TODO to be removed as we are able to convert osm to geojson, we will maintain only geojson enrichment algorithms.
    @Deprecated(forRemoval = true)
    public String aggregateBusStops(JSONArray busStops, String attributes) {
        List<LinkedHashMap> elements = busStops.stream()
                .map(element -> (LinkedHashMap) element)
                .collect(Collectors.toList());

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

            Geometry geoNav = GeometryTools.geometryFactory.createPoint(coordinate);

            enrichPoint(attributes, stopPointNavitia, geoNav, elements);
        });

        return targetApiContext.jsonString();
    }

    private void enrichPoint(String attributes, LinkedHashMap stopPointNavitia, Geometry geoNav, List<LinkedHashMap> elements) {
        // get the closest feature/point to Navitia's bus stop and enrich it
        Object stopId = stopPointNavitia.get("id");
        elements.parallelStream()
                .filter(element ->
                        {
                            Coordinate coordOsm = new Coordinate(
                                    Double.parseDouble(element.get("lon").toString()),
                                    Double.parseDouble(element.get("lat").toString()));

                            Geometry geoOsm = GeometryTools.geometryFactory.createPoint(coordOsm);
                            Object name = ((LinkedHashMap<?, ?>) element.get("tags")).get("name");
                            return name != null
                                    && element.get("type").equals("node") // point type
                                    && (stopPointNavitia.get("name").equals(name) // both points have the same name of are too close to each other
                                    || geoNav.isWithinDistance(geoOsm, 0.001D));
                        }
                )
                // get latest version/changeset only
                .max(Comparator.comparing(element -> (Integer) element.get("changeset")))
                .ifPresent(element -> {
                    // load tags og the current node
                    LinkedHashMap tags = (LinkedHashMap) element.get("tags");

                    // remove white spaces from the attributes list then enrich the additional properties
                    Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                        String property = (String) tags.get(attribute);
                        if (property != null) {
                            // add new attribute to navitia's bus stop equipments
                            ((LinkedHashMap) stopPointNavitia.get("enriched_properties")).put(attribute, property);
                        }
                    });
                    LOGGER.debug("Bus stop : " + tags.get("name") + " v" + element.get("version") + " is close to: " + stopPointNavitia.get("name"));
                });
    }

}

package eu.akka.mobidata.mashup.util;

import com.google.common.base.CaseFormat;
import eu.akka.mobidata.mashup.domain.navitia.StopContainer;
import eu.akka.mobidata.mashup.domain.navitia.Terminu;
import eu.akka.mobidata.mashup.domain.osm.Element;
import eu.akka.mobidata.mashup.domain.osm.OsmContainer;
import eu.akka.mobidata.mashup.domain.osm.Tags;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.LineString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Handles the Openstreetmap format with static pojo models
 *
 * @author Mohamed.KARAMI
 * @deprecated OsmTools will be used instead to dynamically handle osm json schema
 */
public class OsmLegacyTools {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsmLegacyTools.class);

    // kept for compatibility purposes with osm format
    public static void aggregateBusStops(OsmContainer busStops, StopContainer stopContainer, String attributes) {
        // create a line of osm bus stops
        List<Coordinate> busStopCoords = new ArrayList<>();
        busStops.getElements()
                .forEach(element -> busStopCoords.add(new Coordinate(element.getLon(), element.getLat())));
        LineString lineString = GeometryTools.geometryFactory.createLineString(busStopCoords.toArray(new Coordinate[busStopCoords.size()]));

        // for the current navitia stop point we will look for the closest bugs tops on osm line
        Optional.ofNullable(stopContainer.getStopPoint())
                .ifPresent(stopPoint ->
                {
                    Coordinate coord = new Coordinate(
                            stopPoint.getStopArea().getCoord().getLon(),
                            stopPoint.getStopArea().getCoord().getLat());

                    // create a point for the current bus stop
                    Geometry geoNav = GeometryTools.geometryFactory.createPoint(coord);

                    Optional<Element> eltBusStop = busStops.getElements().stream()
                            .filter(element ->
                                    // filter the closest points having less than 0.001D distance
                                    stopPoint.getName().equalsIgnoreCase(element.getTags().getName())
                                            || geoNav.isWithinDistance(GeometryTools.geometryFactory.createPoint(new Coordinate(element.getLon(), element.getLat())), 0.001D))
                            // get the latest version / change set only
                            .max(Comparator.comparing(Element::getChangeset));

                    eltBusStop.ifPresent(element -> {
                        // remove white spaces from the attributes list then enrich the additional properties
                        Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                            try {
                                // fetch property getter by reflection
                                String methodName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, attribute);
                                methodName = "get" + Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
                                Method getter = new PropertyDescriptor(attribute, Tags.class, methodName, null).getReadMethod();
                                Object propertyValue = getter.invoke(element.getTags());

                                // set wheelchair information if exists
                                if (propertyValue != null) {
                                    stopPoint.getAdditionalProperties().put(attribute, propertyValue);
                                }
                            } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                                throw new RuntimeException(e);
                            }
                        });
                        LOGGER.debug("bus stop : " + element.getTags().getName() + " v" + element.getVersion() + " is close to: " + stopPoint.getName());
                    });
                });
    }

    public static void aggregateBusStops(OsmContainer busStops, Terminu terminus, String attributes) {
        // create a line of osm bus stops
        List<Coordinate> busStopCoords = new ArrayList<>();
        busStops.getElements()
                .forEach(element -> busStopCoords.add(new Coordinate(element.getLon(), element.getLat())));
        LineString lineString = GeometryTools.geometryFactory.createLineString(busStopCoords.toArray(new Coordinate[busStopCoords.size()]));

        // for the current navitia stop point we will look for the closest bugs tops on osm line

        Coordinate coord = new Coordinate(
                terminus.getCoord().getLon(),
                terminus.getCoord().getLat());

        // create a point for the current bus stop
        Geometry geoNav = GeometryTools.geometryFactory.createPoint(coord);

        busStops.getElements().stream()
                .filter(element ->
                        // filter the closest points having less than 0.001D distance
                        terminus.getName().equalsIgnoreCase(element.getTags().getName())
                                || geoNav.isWithinDistance(GeometryTools.geometryFactory.createPoint(new Coordinate(element.getLon(), element.getLat())), 0.001D))
                // get the latest version / change set only
                .max(Comparator.comparing(Element::getChangeset))
                .ifPresent(element -> {
                    // remove white spaces from the attributes list then enrich the additional properties
                    Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                        try {
                            // fetch property getter by reflection
                            String methodName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, attribute);
                            methodName = "get" + Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
                            Method getter = new PropertyDescriptor(attribute, Tags.class, methodName, null).getReadMethod();
                            Object propertyValue = getter.invoke(element.getTags());

                            // set wheelchair information if exists
                            if (propertyValue != null) {
                                terminus.getAdditionalProperties().put(attribute, propertyValue);
                            }
                        } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    LOGGER.debug("bus stop : " + element.getTags().getName() + " v" + element.getVersion() + " is close to: " + terminus.getName());
                });
    }
}

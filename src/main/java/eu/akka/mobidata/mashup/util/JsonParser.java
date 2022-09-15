package eu.akka.mobidata.mashup.util;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import eu.akka.mobidata.mashup.model.GenericJsonCriteria;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Handles geoJson parsing
 *
 * @author Mohamed.KARAMI
 */
public class JsonParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonParser.class);
    private DocumentContext targetApiContext;
    private DocumentContext sourceApiContext;

    public JsonParser(String targetApiResponse, String sourceApiResponse) {
        // load target api response to the parser
        this.targetApiContext = JsonPath.parse(targetApiResponse);
        // load source api response to the parser
        this.sourceApiContext = JsonPath.parse(sourceApiResponse);
    }

    /**
     * lookup the closest point to the target point
     *
     * @param attributes
     * @param targetCriteria
     * @param sourceCriteria
     * @return
     */
    public String aggregateJsonStops(String attributes, GenericJsonCriteria targetCriteria, GenericJsonCriteria sourceCriteria) {


        List<LinkedHashMap> targetPlaces = this.targetApiContext.read(targetCriteria.getAttributesParentPath());

        // for the current target stop point we will look for the closest points on the source api response
        targetPlaces.parallelStream().forEach(place ->
        {
            // create empty enriched properties array
            place.put("enriched_properties", new LinkedHashMap());

            enrichPoint(attributes, place, targetCriteria, sourceCriteria);
        });

        return targetApiContext.jsonString();
    }

    /**
     * enrich target point
     *
     * @param attributes
     * @param targetStopPoint
     * @param targetCriteria
     * @param sourceCriteria
     */
    private void enrichPoint(String attributes, LinkedHashMap targetStopPoint, GenericJsonCriteria targetCriteria, GenericJsonCriteria sourceCriteria) {

        Object placeName = targetStopPoint.get("name");

        Geometry geometry = getGeometery(targetStopPoint, targetCriteria);

        List<LinkedHashMap> sourcePlaces = this.sourceApiContext.read(sourceCriteria.getAttributesParentPath());
        if (placeName != null) {
            ((List<LinkedHashMap>) sourcePlaces.get(0)).parallelStream()
                    .filter(sourcePlace ->
                            {
                                String sourceName = getStopName(sourcePlace, sourceCriteria);
                                return sourceName != null
                                        && (placeName.equals(sourceName)
                                        || geometry.isWithinDistance(getGeometery(sourcePlace, sourceCriteria), 0.001D));
                            }
                    )
                    // get latest version/changeset only
                    .max(Comparator.comparing(feature -> getChangeSet(feature)))
                    .ifPresent(sourcePlace -> {
                        String sourceName = getStopName(sourcePlace, sourceCriteria);
                        // remove white spaces from the attributes list then enrich the additional properties
                        Arrays.stream(attributes.replaceAll("\\s", "").split(",")).forEach(attribute -> {
                            String value = getAttributeValue(sourcePlace, attribute, sourceCriteria);
                            // add new attribute to the stop point
                            if (value != null) {
                                setAttribute(targetStopPoint, attribute, value);
                            }
                        });
                        LOGGER.debug("Bus stop : " + sourceName + " v" + getVersion(sourcePlace) + " is close to: " + placeName);
                    });
        }
    }

    /**
     * get the changset of the version
     *
     * @param point
     * @return
     */
    private long getChangeSet(LinkedHashMap point) {
        Object changeSet = 0L;
        try {
            changeSet = point.get("changeset");
            if (changeSet == null) {
                changeSet = ((LinkedHashMap) point.get("tags")).get("changeset");
            }
        } catch (Exception e) {
            try {
                if (changeSet == null) {
                    changeSet = ((LinkedHashMap) point.get("properties")).get("changeset");
                }
            } catch (Exception ignored) {
            }
        }

        return Long.valueOf(changeSet.toString());
    }

    /**
     * get the version of the change
     *
     * @param point
     * @return
     */
    private long getVersion(LinkedHashMap point) {
        Object version = 0L;
        try {
            version = point.get("changeset");
            if (version == null) {
                version = ((LinkedHashMap) point.get("tags")).get("version");
            }
        } catch (Exception e) {
            try {
                if (version == null) {
                    version = ((LinkedHashMap) point.get("properties")).get("version");
                }
            } catch (Exception ignored) {
            }
        }

        return Long.valueOf(version.toString());
    }

    /**
     * get attribute value
     *
     * @param point
     * @param attribute
     * @param criteria
     * @return
     */
    private String getAttributeValue(LinkedHashMap point, String attribute, GenericJsonCriteria criteria) {
        LinkedHashMap attrs = point;
        for (String p : criteria.getEnrichAttributesRootPaths()) {
            attrs = (LinkedHashMap) attrs.get(p);
        }

        return (String) attrs.get(attribute);
    }

    /**
     * get stop name
     *
     * @param point
     * @param criteria
     * @return
     */
    private String getStopName(LinkedHashMap point, GenericJsonCriteria criteria) {
        LinkedHashMap name = point;
        int criteriaCount = criteria.getNamePaths().length;

        // the last element on the tree is a plain text name
        for (int i = 0; i < criteriaCount - 1; i++) {
            name = (LinkedHashMap) name.get(criteria.getNamePaths()[i]);
        }

        return (String) name.get(criteria.getNamePaths()[criteriaCount - 1]);
    }

    /**
     * get geometery from a point
     *
     * @param point
     * @param criteria
     * @return
     */
    private Geometry getGeometery(LinkedHashMap point, GenericJsonCriteria criteria) {
        LinkedHashMap coords = point;
        // go down to the coordinates node
        for (String p : criteria.getCoordsPaths()) {
            coords = (LinkedHashMap) coords.get(p);
        }

        Coordinate coordinate = new Coordinate(
                Double.parseDouble(coords.get("lng") != null ? coords.get("lng").toString() : coords.get("lon").toString()),
                Double.parseDouble(coords.get("lat").toString()));

        return GeometryTools.geometryFactory.createPoint(coordinate);
    }

    /**
     * set enriched attribute
     *
     * @param stopPoint stop point
     * @param attribute attribute
     * @param value     value of the attribute
     */
    private void setAttribute(LinkedHashMap stopPoint, String attribute, String value) {
        LinkedHashMap enriched_properties = (LinkedHashMap) stopPoint.get("enriched_properties");
        enriched_properties.put(attribute, value);
    }
}

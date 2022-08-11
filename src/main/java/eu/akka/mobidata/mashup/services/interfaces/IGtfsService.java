package eu.akka.mobidata.mashup.services.interfaces;

/**
 * Service handling communication with GTFS API
 *
 * @author Mohamed.KARAMI
 */
public interface IGtfsService {

    /**
     * Finds and returns bus stops in geojson format.
     *
     * @param url   url
     * @param token source api Token
     * @return the bus stops if found, or null if not found
     */
    String getGeoJsonFromGtfsBusStops(String url, String token);

    /**
     * Finds and returns bus stops in gtfs format.
     *
     * @param url   url
     * @param token source api Token
     * @return the bus stops if found, or null if not found
     */
    byte[] getGtfsBusStops(String url, String token);
}

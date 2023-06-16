package eu.akka.mobidata.mashup.services.interfaces;

/**
 * Service communicating with the OpenStreetMap API
 *
 * @author Mohamed.KARAMI
 */
public interface IOsmService {

    /**
     * Finds and returns bus stops/lines in geojson format.
     *
     * @param url         url
     * @param token source api Token
     * @return the bus stops/lines if found, or null if not found
     */
    String getGeoJsonFromOsm(String url, String token);

    /**
     * Finds and returns bus stops/lines in json format.
     *
     * @param url         url
     * @param token source api Token
     * @return the bus stops/lines if found, or null if not found
     */
    String getJsonFromOsm(String url, String token);
}

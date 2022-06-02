package eu.akka.mobidata.mashup.services.interfaces;

/**
 * Service communicating with the OpenStreetMap API
 *
 * @author Mohamed.KARAMI
 */
public interface IOsmService {

    /**
     * Finds and returns bus stops in geojson format.
     *
     * @param url         url
     * @param sourceToken source api Token
     * @return the bus stops if found, or null if not found
     */
    String getGeoJsonBusStops(String url, String sourceToken);

    /**
     * Finds and returns bus stops in json format.
     *
     * @param url         url
     * @param sourceToken source api Token
     * @return the bus stops if found, or null if not found
     */
    String getJsonBusStops(String url, String sourceToken);
}

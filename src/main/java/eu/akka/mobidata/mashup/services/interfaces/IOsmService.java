package eu.akka.mobidata.mashup.services.interfaces;

import org.springframework.cache.annotation.Cacheable;

/**
 * Service communicating with the OpenStreetMap API
 *
 * @author Mohamed.KARAMI
 */
public interface IOsmService {

    /**
     * Finds and returns bus stops in geojson format.
     *
     * @param url url
     * @param sourceToken source api Token
     * @return the bus stops if found, or null if not found
     */
    @Cacheable("bus-stops-geojson")
    String getGeoJsonBusStops(String url, String sourceToken);

    /**
     * Finds and returns bus stops in json format.
     *
     * @param url url
     * @param sourceToken source api Token
     * @return the bus stops if found, or null if not found
     */
    @Cacheable("bus-stops-json")
    String getJsonBusStops(String url, String sourceToken);

}

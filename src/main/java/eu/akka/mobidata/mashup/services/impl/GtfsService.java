package eu.akka.mobidata.mashup.services.impl;

import eu.akka.mobidata.mashup.services.interfaces.IGtfsService;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

/**
 * Service handling communication with GTFS API
 *
 * @author Mohamed.KARAMI
 */
@Service
public class GtfsService extends BaseService implements IGtfsService {

    /**
     * Finds and returns bus stops in geojson format.
     *
     * @return the bus stops if found, or null if not found
     */
    @Cacheable("bus-stops-gtfs-geojson")
    public String getGeoJsonFromGtfsBusStops(String url, String token) {
        LOGGER.debug("baseURI: {}", url);
        try {
            byte[] navResponse = getGtfsBusStops(url, token);

            return GeoJsonManager.convertGtfsToGeoJson(navResponse);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Finds and returns bus stops in gtfs format.
     *
     * @return the bus stops if found, or null if not found
     */
    @Cacheable("bus-stops-gtfs")
    public byte[] getGtfsBusStops(String url, String token) {
        LOGGER.debug("baseURI: {}", url);
        try {
            HttpHeaders header = new HttpHeaders();
            header.set("authorization", token);
            header.set("Content-Type", "application/zip");

            HttpEntity<String> entity = new HttpEntity<>(header);
            ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);
            return response.getBody();

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}

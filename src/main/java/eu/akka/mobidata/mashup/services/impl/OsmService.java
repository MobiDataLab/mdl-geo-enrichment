package eu.akka.mobidata.mashup.services.impl;

import eu.akka.mobidata.mashup.services.interfaces.IOsmService;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import eu.akka.mobidata.mashup.util.Json2PojoTools;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Service communicating with the OpenStreetMap API
 *
 * @author Mohamed.KARAMI
 */
@Service
public class OsmService extends BaseService implements IOsmService {

    /**
     * Finds and returns bus stops in geojson format.
     *
     * @return the bus stops if found, or null if not found
     */
    public String getGeoJsonBusStops(String url, String sourceToken) {
        LOGGER.debug("baseURI: {}", url);
        try {
            String navResponse = getJsonBusStops(url, sourceToken);

            // to be used to generate pojo classes based on json response
            // generateOsmPojoClasses(navResponse.getBody());

            return GeoJsonManager.convertOsmToGeoJson(navResponse);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Finds and returns bus stops in json format.
     *
     * @return the bus stops if found, or null if not found
     */
    public String getJsonBusStops(String url, String sourceToken) {
        LOGGER.debug("baseURI: {}", url);
        try {
            setToken(sourceToken);
            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * generate pojo classes based on json/osm format
     *
     * @param OsmResponse OSM response
     */
    private void generateOsmPojoClasses(String OsmResponse) {
        try {
            Path source = Files.createTempDirectory("pojo");

            Json2PojoTools.convertJsonToJavaClass(OsmResponse, source.toFile(), "eu.akka.mobidata.mashup.domain.osm", "OsmContainer");
            LOGGER.debug("POJO files were generated to {}", source);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

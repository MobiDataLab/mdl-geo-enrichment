package eu.akka.mobidata.mashup.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.akka.mobidata.mashup.domain.osm.OsmContainer;
import eu.akka.mobidata.mashup.util.Json2PojoTools;
import eu.akka.mobidata.mashup.util.OsmTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Service communicating with the OpenStreetMap API
 *
 * @author Mohamed.KARAMI
 */
@Service
public class OsmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsmService.class);
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Finds and returns bus stops is osm format.
     *
     * @return the bus stops if found, or null if not found
     */
    @Cacheable("bus-stops-osm")
    public OsmContainer getOsmBusStops(String url) {
        LOGGER.debug("baseURI: {}", url);
        try {
            Object navObject = restTemplate.getForObject(url, Object.class);

            return new ObjectMapper().convertValue(navObject, OsmContainer.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    /**
     * Finds and returns bus stops in geojson format.
     *
     * @return the bus stops if found, or null if not found
     */
    @Cacheable("bus-stops-gj")
    public String getGeoJsonBusStops(String url) {
        LOGGER.debug("baseURI: {}", url);
        try {
            Optional<String> navResponse = Optional.ofNullable(restTemplate.getForObject(url, String.class));
            if (navResponse.isPresent()) {
                // to be used to generate pojo classes based on json response
                // generateOsmPojoClasses(navResponse.get());

                return OsmTools.convertOsmToGeoJson(navResponse.get());
            }

        } catch (RestClientException | IOException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Finds and returns bus stops in json format.
     *
     * @return the bus stops if found, or null if not found
     */
    //@Cacheable("bus-stops-json")
    public String getJsonBusStops(String url) {
        LOGGER.debug("baseURI: {}", url);
        try {
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

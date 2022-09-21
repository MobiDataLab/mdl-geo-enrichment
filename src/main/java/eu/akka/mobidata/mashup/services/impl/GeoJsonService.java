package eu.akka.mobidata.mashup.services.impl;

import eu.akka.mobidata.mashup.exceptions.BadRequestException;
import eu.akka.mobidata.mashup.services.interfaces.IGeoJsonService;
import eu.akka.mobidata.mashup.util.GeoJsonManager;
import org.geotools.geojson.feature.FeatureJSON;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Service communicating with the GeoJson API
 *
 * @author Mohamed.KARAMI
 */
@Service
public class GeoJsonService extends BaseService implements IGeoJsonService {

    @Override
    public String getGeoJsonBusStops(String url, String token) {
        LOGGER.debug("baseURI: {}", url);
        try {
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            header.set("authorization", token);

            HttpEntity<String> entity = new HttpEntity<>(header);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            String busStops = response.getBody();

            // read feature collection schema
            FeatureJSON featureJSON = new FeatureJSON();
            SimpleFeatureType featureType = featureJSON.readFeatureCollectionSchema(busStops, false);

            if (featureType.getAttributeCount() < 1) {
                // assuming it is an osm format try to convert to geojson
                busStops = GeoJsonManager.convertOsmToGeoJson(busStops.getBytes(StandardCharsets.UTF_8));
                featureType = featureJSON.readFeatureCollectionSchema(busStops, false);

                if (featureType.getAttributeCount() < 1) {
                    throw new BadRequestException("Malformed GeoJson response!");
                }
            }

            return busStops;
        } catch (RuntimeException | IOException e) {
            LOGGER.error(e.getMessage());
            throw new BadRequestException("Malformed GeoJson response!");
        }
    }
}

package eu.akka.mobidata.mashup.services.impl;

import eu.akka.mobidata.mashup.services.interfaces.IGeoJsonService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

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
            return response.getBody();

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}

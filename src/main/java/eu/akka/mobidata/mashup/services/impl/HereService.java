package eu.akka.mobidata.mashup.services.impl;

import eu.akka.mobidata.mashup.services.interfaces.IHereService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Service communicating with Here API
 *
 * @author Mohamed.KARAMI
 */
@Service
public class HereService extends BaseService implements IHereService {

    private final String HERE_ROUTE_REQUEST = "/routes?apiKey=API_KEY&origin=ORIGIN&destination=DESTINATION&return=polyline&mode=fastest;bus";
    private final String HERE_STATIONS_REQUEST = "/stations?apiKey=API_KEY&in=LOCATION&r=1000&return=transport&maxPlaces=50";

    @Override
    public String findHereRoutes(String apiKey, String fromCoordinates, String toCoordinates) {
        String request = HERE_ROUTE_REQUEST.replace("API_KEY", apiKey).replace("ORIGIN", fromCoordinates).replace("DESTINATION", toCoordinates);
        String url = URLDecoder.decode(endPointConfig.getHereUri().concat(request), StandardCharsets.UTF_8);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }

    @Override
    public String findNearStations(String apiKey, String coordinates) {
        String request = HERE_STATIONS_REQUEST.replace("API_KEY", apiKey).replace("LOCATION", coordinates);
        String url = URLDecoder.decode(endPointConfig.getHereUri().concat(request), StandardCharsets.UTF_8);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(header);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return response.getBody();
    }
}

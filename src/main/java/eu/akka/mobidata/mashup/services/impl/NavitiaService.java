package eu.akka.mobidata.mashup.services.impl;

import eu.akka.mobidata.mashup.exceptions.BadRequestException;
import eu.akka.mobidata.mashup.services.interfaces.INavitiaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Service communicating with the Navitia API
 *
 * @author Mohamed.KARAMI
 */
@Service
public class NavitiaService extends BaseService implements INavitiaService {

    private static final String API_LINES = "/coverage/fr-idf/lines?from=from-coord&to=to-coord";
    private static final String API_JOURNEYS = "/journeys?from=from-coord&to=to-coord&allowed_id[]=physical_mode:Bus";

    /**
     * Finds and returns lines.
     *
     * @param targetToken     target Token
     * @param fromCoordinates from Coordinates
     * @param toCoordinates   to Coordinates
     * @return the lines if found, or null if not found
     */
    public String findLines(String targetToken, String fromCoordinates, String toCoordinates) {

        try {
            String[] from = StringUtils.deleteWhitespace(fromCoordinates).split(",");
            String[] to = StringUtils.deleteWhitespace(toCoordinates).split(",");

            if (from.length < 2 && to.length < 2) {
                throw new BadRequestException("Malformed from/to coordinates!");
            }

            String urlRequest = API_LINES.replace("from-coord", from[1] + ";" + from[0]);
            urlRequest = urlRequest.replace("to-coord", to[1] + ";" + to[0]);

            String url = URLDecoder.decode(endPointConfig.getNavitiaUri().concat(urlRequest), StandardCharsets.UTF_8);
            LOGGER.debug("baseURI: {}", endPointConfig.getNavitiaUri());

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            header.set("authorization", targetToken);

            HttpEntity<String> entity = new HttpEntity<>(header);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    /**
     * Finds and returns journeys.
     *
     * @param targetToken     target Token
     * @param fromCoordinates from Coordinates
     * @param toCoordinates   to Coordinates
     * @return the journeys if found, or null if not found
     */
    public String findJsonJourneys(String targetToken, String fromCoordinates, String toCoordinates) {

        try {
            String[] from = StringUtils.deleteWhitespace(fromCoordinates).split(",");
            String[] to = StringUtils.deleteWhitespace(toCoordinates).split(",");

            if (from.length < 2 && to.length < 2) {
                throw new BadRequestException("Malformed from/to coordinates!");
            }

            String urlRequest = API_JOURNEYS.replace("from-coord", from[1] + ";" + from[0]);
            urlRequest = urlRequest.replace("to-coord", to[1] + ";" + to[0]);

            String url = URLDecoder.decode(endPointConfig.getNavitiaUri().concat(urlRequest), StandardCharsets.UTF_8);
            LOGGER.debug("baseURI: {}", endPointConfig.getNavitiaUri());

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            header.set("authorization", targetToken);

            HttpEntity<String> entity = new HttpEntity<>(header);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

}

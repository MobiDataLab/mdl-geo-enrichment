package eu.akka.mobidata.mashup.services.impl;

import eu.akka.mobidata.mashup.exceptions.BadRequestException;
import eu.akka.mobidata.mashup.services.interfaces.INavitiaService;
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

    private static final String API_LINES = "/coverage/sandbox/lines?from=2.2433581;48.8345631&to=2.4400646;48.8775033";
    private static final String API_JOURNEYS = "/journeys?from=from_coord&to=to_coord&allowed_id[]=physical_mode:Bus";

    /**
     * Finds and returns lines.
     *
     * @return the lines if found, or null if not found
     */
    public String findLines() {

        try {
            String url = URLDecoder.decode(endPointConfig.getNavitiaUri().concat(API_LINES), StandardCharsets.UTF_8);
            LOGGER.debug("baseURI: {}", url);

            return restTemplate.getForObject(url, String.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    /**
     * Finds and returns journeys.
     *
     * @param targetToken target Token
     * @param fromCoordinates from Coordinates
     * @param toCoordinates to Coordinates
     * @return the journeys if found, or null if not found
     */
    public String findJsonJourneys(String targetToken, String fromCoordinates, String toCoordinates) {
        LOGGER.debug("baseURI: {}", endPointConfig.getNavitiaUri());
        try {
            String[] from = fromCoordinates.split(",");
            String[] to = toCoordinates.split(",");

            if (from.length < 2 && to.length < 2) {
                throw new BadRequestException("Malformed from/to coordinates!");
            }

            String urlRequest = API_JOURNEYS.replace("from_coord", from[1] + ";" + from[0]);
            urlRequest = urlRequest.replace("to_coord", to[1] + ";" + to[0]);

            String url = URLDecoder.decode(endPointConfig.getNavitiaUri().concat(urlRequest), StandardCharsets.UTF_8);
            this.token = targetToken;
            return restTemplate.getForObject(url, String.class);

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }
    }

}

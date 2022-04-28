package eu.akka.mobidata.mashup.services;

import eu.akka.mobidata.mashup.config.EndPointConfig;
import eu.akka.mobidata.mashup.config.TokenConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * Service communicating with the Navitia API
 *
 * @author Mohamed.KARAMI
 */
@Service
public class NavitiaService {

    private static final String API_LINES = "/coverage/sandbox/lines?from=1.3951577;43.5690569&to=1.4803165;43.6283803";
    private static final String API_JOURNEYS = "/journeys?from=1.3951577;43.5690569&to=1.4803165;43.6283803&allowed_id[]=physical_mode:Bus";

    private static final Logger LOGGER = LoggerFactory.getLogger(NavitiaService.class);

    @Autowired
    private EndPointConfig endPointConfig;
    @Autowired
    private TokenConfig tokenConfig;

    @Autowired
    @Qualifier("NavRestTemplate")
    private RestTemplate restTemplate;

    @Bean(name = "NavRestTemplate")
    public RestTemplate NavRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(endPointConfig.getNavitiaUri())
                .additionalInterceptors((request, body, execution) -> {
                    // it seems user token does not have enough permissions to interact with sandbox apis, we use the provided documentation's token instead
                    String token = request.getURI().getPath().contains("sandbox") ? tokenConfig.getNavitiaDocToken() : tokenConfig.getNavitiaUserToken();
                    request.getHeaders().add("Authorization", token);
                    return execution.execute(request, body);
                }).build();
    }

    /**
     * Finds and returns lines.
     *
     * @return the lines if found, or null if not found
     */
    @Cacheable("lines")
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
     * @return the journeys if found, or null if not found
     */
    @Cacheable("journeys-json")
    public String findJsonJourneys() {
        LOGGER.debug("baseURI: {}", endPointConfig.getNavitiaUri());
        try {
            String url = URLDecoder.decode(endPointConfig.getNavitiaUri().concat(API_JOURNEYS), StandardCharsets.UTF_8);
            return restTemplate.getForObject(url, String.class);

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

}

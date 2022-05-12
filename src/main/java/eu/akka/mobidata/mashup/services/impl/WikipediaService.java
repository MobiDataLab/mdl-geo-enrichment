package eu.akka.mobidata.mashup.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import eu.akka.mobidata.mashup.config.EndPointConfig;
import eu.akka.mobidata.mashup.services.interfaces.IWikipediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Service communicating with the Wikipedia API.
 *
 * @author christer
 */
@Service

public class WikipediaService implements IWikipediaService {
    private static final String API_PATH = "/w/api.php?action=query&format=json&prop=extracts&exintro=true&redirects=true&titles={title}";

    private static final Logger LOGGER = LoggerFactory.getLogger(WikipediaService.class);

    @Autowired
    private EndPointConfig endPointConfig;

    /**
     * Returns an extract (short version) of the description of a page in
     * Wikipedia
     *
     * @param title the title of the Wikipedia page to get the extract from
     * @return the extract of the page or null if not found
     */
    public String getExtract(String title) {
        RestTemplate mb = new RestTemplate();

        LOGGER.debug("baseURI: {}", endPointConfig.getWikidataUri());
        try {
            JsonNode root = mb.getForObject(endPointConfig.getWikidataUri().concat(API_PATH), JsonNode.class, title);

            List<String> extracts = root.findValuesAsText("extract");
            if (!extracts.isEmpty()) {
                return extracts.get(0);
            } else {
                return null;
            }
        } catch (RestClientException e) {
            LOGGER.debug(e.getMessage());
            return null;
        }
    }

}

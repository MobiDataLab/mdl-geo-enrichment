package eu.akka.mobidata.mashup.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * Handles the apis endpoints configuration.
 *
 * @author Mohamed.KARAMI
 */
@Configuration
@ConfigurationProperties(prefix = "endpoints")
public class EndPointConfig {

    private String navitiaUri;
    private String overpassUri;
    private String wikidataUri;

    private RestTemplate restTemplate;

    public RestTemplate getRestTemplate() {
        if (this.restTemplate == null) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
            this.restTemplate = restTemplateBuilder
                    .setReadTimeout(Duration.ofSeconds(60))
                    .build();
        }
        return this.restTemplate;
    }

    public String getNavitiaUri() {
        return navitiaUri;
    }

    public void setNavitiaUri(String navitiaUri) {
        this.navitiaUri = navitiaUri;
    }

    public String getOverpassUri() {
        return overpassUri;
    }

    public void setOverpassUri(String overpassUri) {
        this.overpassUri = overpassUri;
    }

    public String getWikidataUri() {
        return wikidataUri;
    }

    public void setWikidataUri(String wikidataUri) {
        this.wikidataUri = wikidataUri;
    }
}
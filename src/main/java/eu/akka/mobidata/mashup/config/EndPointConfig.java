package eu.akka.mobidata.mashup.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
    private String hereUri;

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

    public String getHereUri() {
        return hereUri;
    }

    public void setHereUri(String hereUri) {
        this.hereUri = hereUri;
    }
}
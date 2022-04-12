package eu.akka.mobidata.mashup.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "endpoints")
public class EndPointConfig {

    private String navitiaUri;
    private String overpassUri;
    private String wikidataUri;

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
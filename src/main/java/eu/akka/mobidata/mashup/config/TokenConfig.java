package eu.akka.mobidata.mashup.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Handles the token configuration.
 *
 * @author Mohamed.KARAMI
 */
@Deprecated // tokens will be passed as api parameters
@Configuration
@ConfigurationProperties(prefix = "tokens")
public class TokenConfig {

    private String navitiaUserToken;
    private String navitiaDocToken;

    public String getNavitiaUserToken() {
        return navitiaUserToken;
    }

    public void setNavitiaUserToken(String navitiaUserToken) {
        this.navitiaUserToken = navitiaUserToken;
    }

    public String getNavitiaDocToken() {
        return navitiaDocToken;
    }

    public void setNavitiaDocToken(String navitiaDocToken) {
        this.navitiaDocToken = navitiaDocToken;
    }
}
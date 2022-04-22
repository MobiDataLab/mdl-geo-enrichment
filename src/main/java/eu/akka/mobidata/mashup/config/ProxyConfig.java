package eu.akka.mobidata.mashup.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Handles the proxy configuration.
 *
 * @author Mohamed.KARAMI
 */
@Configuration
@ConfigurationProperties(prefix = "proxy")
public class ProxyConfig {

    private String proxyHost;
    private String proxyPort;

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(String proxyPort) {
        this.proxyPort = proxyPort;
    }
}
package eu.akka.mobidata.mashup;

import eu.akka.mobidata.mashup.config.ProxyConfig;
import eu.akka.mobidata.mashup.security.TrustAllX509TrustManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Application
 *
 * @author Mohamed.KARAMI
 */
@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
public class Application {

    @Autowired
    private ProxyConfig proxyConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void setProxy() {
        if (proxyConfig.getProxyHost() != null) {
            System.setProperty("https.proxyHost", proxyConfig.getProxyHost());
            System.setProperty("https.proxyPort", proxyConfig.getProxyPort());

            // to be used only for debugging if the used jdk does not accept selfsigned certificates
            //disableSSLVerification();
        }
    }

    // bypass SSL verification
    public static void disableSSLVerification() {
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new TrustAllX509TrustManager()}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((string, ssl) -> true);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

}

package eu.akka.mobidata.mashup.services.impl;

import eu.akka.mobidata.mashup.config.EndPointConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * Base Service for mobidata api services
 *
 * @author Mohamed.KARAMI
 */
public class BaseService {
    static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    EndPointConfig endPointConfig;

    @Autowired
    @Qualifier("MdRestTemplate")
    RestTemplate restTemplate;

    String token;

    @Bean(name = "MdRestTemplate")
    RestTemplate MdRestTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(endPointConfig.getNavitiaUri())
                .additionalInterceptors((request, body, execution) -> {
                    if (token != null) {
                        request.getHeaders().add("Authorization", token);
                    }
                    return execution.execute(request, body);
                }).setReadTimeout(Duration.ofSeconds(60)).build();
    }

}

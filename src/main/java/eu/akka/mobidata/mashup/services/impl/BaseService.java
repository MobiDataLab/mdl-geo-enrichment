package eu.akka.mobidata.mashup.services.impl;

import eu.akka.mobidata.mashup.config.EndPointConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

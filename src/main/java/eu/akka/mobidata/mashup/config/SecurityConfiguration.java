package eu.akka.mobidata.mashup.config;

import eu.akka.mobidata.mashup.util.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Handles Security Configuration
 *
 * @author Mohamed.KARAMI
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable basic authentication, it will be managed at the gateway level
        http.httpBasic().disable();
    }
}
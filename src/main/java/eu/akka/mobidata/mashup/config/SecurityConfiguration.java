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
        http.authorizeRequests()
                .antMatchers(Constants.AUTH_LIST)
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic();
    }
}
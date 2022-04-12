package eu.akka.mobidata.mashup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swaggerPersonApiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("MobiDataMashup")
                .select()
                .apis(RequestHandlerSelectors.basePackage("eu.akka.mobidata.mashup.controllers"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build();
    }
}

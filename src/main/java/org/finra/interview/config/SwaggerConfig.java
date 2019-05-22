package org.finra.interview.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableAutoConfiguration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String title;


    @Bean
    public Docket postsApi() {
        String title = String.format("%s API", this.title);
        String description = String.format("%s reference for client applications.", title);

        ApiInfo apiInfo = new ApiInfoBuilder().title(title)
                .description(description)
                .build();

        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo)
                .enable(true)
                .select()
                .paths(regex("/api.*"))
                .build();
    }
}

package com.vgalloy.server.configuration;

import java.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;
/**
 * Created by Vincent Galloy on 31/03/16.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger Api configuration.
     *
     * @return The Swagger api configuration.
     */
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, newArrayList(
                        new ResponseMessageBuilder().code(200).message("OK").responseModel(new ModelRef("Success")).build(),
                        new ResponseMessageBuilder().code(404).message("Not Found").responseModel(new ModelRef("Error")).build(),
                        new ResponseMessageBuilder().code(401).message("Unauthorized").responseModel(new ModelRef("Error")).build(),
                        new ResponseMessageBuilder().code(500).message("Internal Server Error").responseModel(new ModelRef("Error")).build()))
                .enableUrlTemplating(true)
                .tags(new Tag("Pet Service", "All apis relating to pets"));
    }
}

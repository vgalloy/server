package com.vgalloy.server.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
@Configuration
@Import({CommonConfiguration.class, SwaggerConfig.class})
public class StartServer {

    /**
     * The main method.
     *
     * @param args The arguments list
     */
    public static void main(String... args) {
        SpringApplication.run(StartServer.class, args);
    }
}

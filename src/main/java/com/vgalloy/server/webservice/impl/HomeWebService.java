package com.vgalloy.server.webservice.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 18/12/15.
 */
@RestController
@RequestMapping("home")
@PropertySource(value = "classpath:META-INF/maven/com.vgalloy/server/pom.properties", ignoreResourceNotFound = true)
public class HomeWebService {

    @Value("${project.type}")
    private String type;

    @Value("${version:none}")
    private String version;

    /**
     * Requête HTTP GET : obtention de la page d'accueil.
     *
     * @return Un String avec quelque informations
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "Server is running !<p>" +
                "Version : " + version + "<p>" +
                "Type : " + type;
    }
}

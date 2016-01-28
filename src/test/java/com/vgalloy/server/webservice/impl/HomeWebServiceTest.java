package com.vgalloy.server.webservice.impl;

import com.jayway.restassured.RestAssured;
import com.vgalloy.server.StartServer;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.when;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 22/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StartServer.class)
@WebAppConfiguration
@IntegrationTest("server.port:0") // Trouve n'importe quel port de libre pour lancer le server de test
public class HomeWebServiceTest {
    @Value("${local.server.port}")// Trouve le port associé au server de test
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testHomePage() {
        when().get("/home")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
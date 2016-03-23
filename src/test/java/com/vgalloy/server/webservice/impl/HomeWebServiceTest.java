package com.vgalloy.server.webservice.impl;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.vgalloy.server.webservice.AbstractWebServiceImplTest;

import static com.jayway.restassured.RestAssured.when;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 22/12/15.
 */
public class HomeWebServiceTest extends AbstractWebServiceImplTest {

    @Test
    public void testHomePage() {
        when().get("/home")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
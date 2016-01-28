package com.vgalloy.server.webservice.impl;

import com.jayway.restassured.RestAssured;
import com.vgalloy.server.StartServer;
import com.vgalloy.server.aspect.security.SecurityException;
import com.vgalloy.server.webservice.AbstractWebServiceImplTest;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 28/01/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StartServer.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class CalendarWebServiceImplTest extends AbstractWebServiceImplTest {

    @Value("${local.server.port}")
    private int port;

    @Before
    public void setUp() {
        super.setUp();
        RestAssured.port = port;
    }

    @Test
    public void testGetCalendarWithoutCredential() {
        when().get("/calendar")
                .then().statusCode(HttpStatus.SC_FORBIDDEN).assertThat().body(equalTo(SecurityException.UNAUTHORIZED));
    }

    @Test
    public void testGetCalendarWithUserCredential() {
        given().auth().preemptive().basic(USER, USER)
                .when().get("/calendar")
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo(""));
    }

}
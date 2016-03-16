package com.vgalloy.server.webservice.impl;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.vgalloy.server.aspect.security.SecurityException;
import com.vgalloy.server.webservice.AbstractWebServiceImplTest;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 28/01/16.
 */
public class CalendarWebServiceImplTest extends AbstractWebServiceImplTest {

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
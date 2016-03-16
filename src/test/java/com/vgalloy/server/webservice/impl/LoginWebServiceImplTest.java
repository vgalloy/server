package com.vgalloy.server.webservice.impl;

import org.apache.http.HttpStatus;
import org.junit.Test;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.http.ContentType;
import com.vgalloy.server.aspect.security.SecurityException;
import com.vgalloy.server.webservice.AbstractWebServiceImplTest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 22/12/15.
 */
public class LoginWebServiceImplTest extends AbstractWebServiceImplTest {

    private static final JsonNodeFactory JSON_NODE_FACTORY = JsonNodeFactory.instance;
    private static final String LOGIN_URL = "/security/login";
    private static final String GOOGLE_URL = "/security/url";
    private static final String TOKEN_URL = "/security/token";

    @Test
    public void testLoginOk() {
        ObjectNode node = JSON_NODE_FACTORY.objectNode();
        node.put("username", USER).put("password", USER);
        given().contentType(ContentType.JSON).body(node)
                .when().post(LOGIN_URL)
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("true"));
    }

    @Test
    public void testLoginWithEmptyPassword() {
        ObjectNode node = JSON_NODE_FACTORY.objectNode();
        node.put("username", USER).put("password", "");
        given().contentType(ContentType.JSON).body(node)
                .when().post(LOGIN_URL)
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("false"));
    }

    @Test
    public void testLoginWithEmptyUsernameAndEmtyPassword() {
        ObjectNode node = JSON_NODE_FACTORY.objectNode();
        node.put("username", "").put("password", "");
        given().contentType(ContentType.JSON).body(node)
                .when().post(LOGIN_URL)
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("false"));
    }

    @Test
    public void testLoginWithEmptyRequest() {
        ObjectNode node = JSON_NODE_FACTORY.objectNode();
        given().contentType(ContentType.JSON).body(node)
                .when().post(LOGIN_URL)
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("false"));
    }

    @Test
    public void testGetUrlWithAdminCredential() {
        given().auth().preemptive().basic(ADMIN, ADMIN)
                .when().get(GOOGLE_URL)
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(startsWith("https://accounts.google.com/o/oauth2/auth"));
    }

    @Test
    public void testGetUrlWithUserCredential() {
        given().auth().preemptive().basic(USER, USER)
                .when().get(GOOGLE_URL)
                .then().statusCode(HttpStatus.SC_FORBIDDEN).assertThat().body(equalTo(SecurityException.UNAUTHORIZED));
    }

    @Test
    public void testGetUrlWithoutCredential() {
        given()
                .when().get(GOOGLE_URL)
                .then().statusCode(HttpStatus.SC_FORBIDDEN).assertThat().body(equalTo(SecurityException.UNAUTHORIZED));
    }

    @Test
    public void testSetTokenWithoutCredential() {
        given().contentType(ContentType.JSON).body("eRetteEfds")
                .when().post(TOKEN_URL)
                .then().statusCode(HttpStatus.SC_FORBIDDEN).assertThat().body(equalTo(SecurityException.UNAUTHORIZED));
    }

    @Test
    public void testSetTokenWithUserCredential() {
        given().auth().preemptive().basic(USER, USER).contentType(ContentType.JSON).body("eRetteEfds")
                .when().post(TOKEN_URL)
                .then().statusCode(HttpStatus.SC_FORBIDDEN).assertThat().body(equalTo(SecurityException.UNAUTHORIZED));
    }

    @Test
    public void testSetTokenWithAdminCredential() {
        given().auth().preemptive().basic(ADMIN, ADMIN).contentType(ContentType.JSON).body("eRetteEfds")
                .when().post(TOKEN_URL)
                .then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }
}
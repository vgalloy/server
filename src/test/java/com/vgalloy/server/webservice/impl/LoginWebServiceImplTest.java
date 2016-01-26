package com.vgalloy.server.webservice.impl;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 22/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StartServer.class)
@WebAppConfiguration
@IntegrationTest("server.port:0") // Trouve n'importe quel port de libre pour lancer le server de test
public class LoginWebServiceImplTest extends AbstractWebServiceImplTest {
    private static final JsonNodeFactory factory = JsonNodeFactory.instance;
    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    private static final String LOGIN_URL = "/security/login";
    private static final String GOOGLE_URL = "/security/url";
    private static final String TOKEN_URL = "/security/token";
    @Value("${local.server.port}") // Trouve le port associé au server de test
    private int port;

    @Before
    public void setUp() {
        super.setUp();
        RestAssured.port = port;
    }

    @Test
    public void testLoginOk() {
        ObjectNode node = factory.objectNode();
        node.put("username", USER).put("password", USER);
        given().contentType(ContentType.JSON).body(node)
                .when().post(LOGIN_URL)
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("true"));
    }

    @Test
    public void testLoginWithEmptyPassword() {
        ObjectNode node = factory.objectNode();
        node.put("username", USER).put("password", "");
        given().contentType(ContentType.JSON).body(node)
                .when().post(LOGIN_URL)
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("false"));
    }

    @Test
    public void testLoginWithEmptyUsernameAndEmtyPassword() {
        ObjectNode node = factory.objectNode();
        node.put("username", "").put("password", "");
        given().contentType(ContentType.JSON).body(node)
                .when().post(LOGIN_URL)
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("false"));
    }

    @Test
    public void testLoginWithEmptyRequest() {
        ObjectNode node = factory.objectNode();
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
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("false"));
    }
}
package com.vgalloy.server.webservice.impl;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.vgalloy.server.StartServer;
import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.model.entity.User;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 22/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StartServer.class)
@WebAppConfiguration
@IntegrationTest("server.port:0") // Trouve n'importe quel port de libre pour lancer le server de test
public class LoginWebServiceImplTest {
    private static final String USER = "USER";
    private static final JsonNodeFactory factory = JsonNodeFactory.instance;
    // Trouve le port associé au server de test
    @Value("${local.server.port}")
    int port;
    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() {
        RestAssured.port = port;
        userDao.removeAll();
        User user = new User(USER, USER, SecurityLevel.USER);
        userDao.create(user);
    }

    @Test
    public void testOk() {
        ObjectNode node = factory.objectNode();
        node.put("username", USER).put("password", USER);
        given().contentType(ContentType.JSON).body(node)
                .when().post("/login")
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("true"));
    }

    @Test
    public void testEmptyPassword() {
        ObjectNode node = factory.objectNode();
        node.put("username", USER).put("password", "");
        given().contentType(ContentType.JSON).body(node)
                .when().post("/login")
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("false"));
    }

    @Test
    public void testEmptyUsernameAndEmtyPassword() {
        ObjectNode node = factory.objectNode();
        node.put("username", "").put("password", "");
        given().contentType(ContentType.JSON).body(node)
                .when().post("/login")
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("false"));
    }

    @Test
    public void testEmptyRequest() {
        ObjectNode node = factory.objectNode();
        given().contentType(ContentType.JSON).body(node)
                .when().post("/login")
                .then().statusCode(HttpStatus.SC_OK).assertThat().body(equalTo("false"));
    }
}
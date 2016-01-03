package com.vgalloy.server.webservice.impl;

import com.jayway.restassured.RestAssured;
import com.vgalloy.server.StartServer;
import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.model.entity.User;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
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

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 22/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StartServer.class)
@WebAppConfiguration
@IntegrationTest("server.port:0") // Trouve n'importe quel port de libre pour lancer le server de test
public class UserWebServiceImplTest {
    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";
    // Trouve le port associé au server de test
    @Value("${local.server.port}")
    int port;

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() {
        userDao.removeAll();
        User admin = new User(ADMIN, ADMIN, SecurityLevel.ADMIN);
        userDao.create(admin);
        User user = new User(USER, USER, SecurityLevel.USER);
        userDao.create(user);
        RestAssured.port = port;
    }

    @Test
    public void testGetAllAnonymous() {
        given()
                .when().get("/user")
                .then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void testGetAllWrongUser() {
        given().auth().preemptive().basic("test", "test")
                .when().get("/user")
                .then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void testGetAllWrongPassword() {
        given().auth().preemptive().basic(ADMIN, USER)
                .when().get("/user")
                .then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void testGetAllUser() {
        given().auth().preemptive().basic(USER, USER)
                .when().get("/user")
                .then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void testGetAllAdmin() {
        given().auth().preemptive().basic(ADMIN, ADMIN)
                .when().get("/user")
                .then().statusCode(HttpStatus.SC_OK).body("username", Matchers.hasItems(ADMIN, USER));
    }

    @Test
    public void testGetAdminWithAnonymous() {
        given()
                .when().get("/user/{username}", ADMIN)
                .then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void testGetAdminWithUser() {
        given().auth().preemptive().basic(USER, USER)
                .when().get("/user/{username}", ADMIN)
                .then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void testGetUserWithUser() {
        given().auth().preemptive().basic(USER, USER)
                .when().get("/user/{username}", USER)
                .then().statusCode(HttpStatus.SC_OK).body("username", Matchers.is(USER)).body("password", Matchers.is(USER)).body("role", Matchers.is(SecurityLevel.USER.toString()));
    }

    @Test
    public void testGetUserWithAdmin() {
        given().auth().preemptive().basic(ADMIN, ADMIN)
                .when().get("/user/{username}", USER)
                .then().statusCode(HttpStatus.SC_OK).body("username", Matchers.is(USER)).body("password", Matchers.is(USER)).body("role", Matchers.is(SecurityLevel.USER.toString()));
    }

    @Test
    public void testGetAdminWithAdmin() {
        given().auth().preemptive().basic(ADMIN, ADMIN)
                .when().get("/user/{username}", ADMIN)
                .then().statusCode(HttpStatus.SC_OK).body("username", Matchers.is(ADMIN)).body("password", Matchers.is(ADMIN)).body("role", Matchers.is(SecurityLevel.ADMIN.toString()));
    }

    @Test
    public void testChangePasswordUserWithAnonymous() {
        given().body("newPassword")
                .when().put("/user/{username}/changePassword", USER)
                .then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void testChangePasswordUserWithUser() {
        given().auth().preemptive().basic(USER, USER).body("newPassword")
                .when().put("/user/{username}/changePassword", USER)
                .then().statusCode(HttpStatus.SC_OK).body("username", Matchers.is(USER)).body("password", Matchers.is("newPassword")).body("role", Matchers.is(SecurityLevel.USER.toString()));
    }

    @Test
    public void testChangePasswordAdminWithUser() {
        given().auth().preemptive().basic(USER, USER).body("newPassword")
                .when().put("/user/{username}/changePassword", ADMIN)
                .then().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void testChangePasswordUserWithAdmin() {
        given().auth().preemptive().basic(ADMIN, ADMIN).body("newPassword")
                .when().put("/user/{username}/changePassword", USER)
                .then().statusCode(HttpStatus.SC_OK).body("username", Matchers.is(USER)).body("password", Matchers.is("newPassword")).body("role", Matchers.is(SecurityLevel.USER.toString()));
    }

    @Test
    public void testChangePasswordAdminWithAdmin() {
        given().auth().preemptive().basic(ADMIN, ADMIN).body("newPassword")
                .when().put("/user/{username}/changePassword", ADMIN)
                .then().statusCode(HttpStatus.SC_OK).body("username", Matchers.is(ADMIN)).body("password", Matchers.is("newPassword")).body("role", Matchers.is(SecurityLevel.ADMIN.toString()));
    }
}
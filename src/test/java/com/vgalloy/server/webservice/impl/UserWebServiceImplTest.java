package com.vgalloy.server.webservice.impl;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.http.ContentType;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.model.entity.User;
import com.vgalloy.server.service.error.Error;
import com.vgalloy.server.service.error.Errors;
import com.vgalloy.server.service.exception.ServiceException;
import com.vgalloy.server.service.validator.UserServiceValidator;
import com.vgalloy.server.webservice.AbstractWebServiceImplTest;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 22/12/15.
 */

public class UserWebServiceImplTest extends AbstractWebServiceImplTest {

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

    @Test
    public void testChangePasswordAdminWithAdminAndWrongPassword() {
        given().auth().preemptive().basic(ADMIN, ADMIN).body("")
                .when().put("/user/{username}/changePassword", ADMIN)
                .then().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(Matchers.is(GlobalExceptionHandler.INTERNAL_SERVER_ERROR));
    }

    @Test
    public void testCreateUserOk() throws Exception {
        given().auth().preemptive().basic(ADMIN, ADMIN).contentType(ContentType.JSON).body(new User("useless", "password", SecurityLevel.ADMIN))
                .when().put("/user/username34")
                .then().statusCode(HttpStatus.SC_CREATED).assertThat().body(equalTo(new ObjectMapper().writeValueAsString(new User("username34", "password", SecurityLevel.ADMIN))));
    }

    @Test
    public void testCreateUserWithEmptyPassword() {
        given().auth().preemptive().basic(ADMIN, ADMIN).contentType(ContentType.JSON).body(new User("useless", "", SecurityLevel.ADMIN))
                .when().put("/user/username35")
                .then().statusCode(HttpStatus.SC_NOT_ACCEPTABLE).assertThat().body(equalTo(new ServiceException(new Errors().addError(new Error(UserServiceValidator.PASSWORD_EMPTY))).getMessage()));
    }

    @Test
    public void testDeleteUser() {
        given().auth().preemptive().basic(ADMIN, ADMIN).contentType(ContentType.JSON)
                .when().delete("/user/USER")
                .then().statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void testDeleteUnExistingUser() {
        given().auth().preemptive().basic(ADMIN, ADMIN).contentType(ContentType.JSON)
                .when().delete("/user/USER_123")
                .then().statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
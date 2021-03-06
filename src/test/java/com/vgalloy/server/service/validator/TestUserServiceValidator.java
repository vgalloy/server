package com.vgalloy.server.service.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.configuration.CommonConfiguration;
import com.vgalloy.server.model.entity.User;
import com.vgalloy.server.service.error.Errors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 12/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CommonConfiguration.class)
public class TestUserServiceValidator {

    @Autowired
    private UserServiceValidator userServiceValidator;

    @Test
    public void testCreationOk() {
        Errors errors = userServiceValidator.checkCreateOrUpdate(new User("test", "test"));
        assertFalse(errors.hasError());
    }

    @Test
    public void testCreationWithNullUser() {
        Errors errors = userServiceValidator.checkCreateOrUpdate(null);
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testCreationWithEmptyUsername() {
        Errors errors = userServiceValidator.checkCreateOrUpdate(new User("  ", "password"));
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testCreationWithNullUsername() {
        Errors errors = userServiceValidator.checkCreateOrUpdate(new User(null, "password"));
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testCreationWithEmptyPassword() {
        Errors errors = userServiceValidator.checkCreateOrUpdate(new User("login", "  "));
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testCreationWithNullPassword() {
        Errors errors = userServiceValidator.checkCreateOrUpdate(new User("login", null));
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testChangePasswordOk() {
        Errors errors = userServiceValidator.checkChangePassword(new User("login", "password"), "pass");
        assertFalse(errors.hasError());
    }

    @Test
    public void testChangePasswordWithEmptyPassword() {
        Errors errors = userServiceValidator.checkChangePassword(new User("login", "password"), " ");
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testChangePasswordWithNullPassword() {
        Errors errors = userServiceValidator.checkChangePassword(new User("login", "password"), null);
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testChangePasswordWithNullUser() {
        Errors errors = userServiceValidator.checkChangePassword(null, "pass");
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testChangeRoleOk() {
        Errors errors = userServiceValidator.checkChangeRole(new User("login", "password"), SecurityLevel.ADMIN);
        assertFalse(errors.hasError());
    }

    @Test
    public void testChangeRoleOkWithNullRole() {
        Errors errors = userServiceValidator.checkChangeRole(new User("login", "password"), null);
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testChangeRoleWithNullUser() {
        Errors errors = userServiceValidator.checkChangeRole(null, SecurityLevel.ADMIN);
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }
}

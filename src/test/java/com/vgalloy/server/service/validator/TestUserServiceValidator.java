package com.vgalloy.server.service.validator;

import com.vgalloy.server.StartServer;
import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.error.Errors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 12/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StartServer.class, loader = SpringApplicationContextLoader.class)
public class TestUserServiceValidator {

    @Autowired
    private UserServiceValidator userServiceValidator;

    @Test
    public void testCreationOk() {
        Errors errors = userServiceValidator.checkUserOkForCreate(new User("test", "test"));
        assertFalse(errors.hasError());
    }

    @Test
    public void testCreationWithNullUser() {
        Errors errors = userServiceValidator.checkUserOkForCreate(null);
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testCreationWithEmptyUsername() {
        Errors errors = userServiceValidator.checkUserOkForCreate(new User("  ", "password"));
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testCreationWithNullUsername() {
        Errors errors = userServiceValidator.checkUserOkForCreate(new User(null, "password"));
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testUpdateOk() {
        Errors errors = userServiceValidator.checkUserOkForCreate(new User("test", "test"));
        assertFalse(errors.hasError());
    }

    @Test
    public void testUpdateWithEmptyUsername() {
        Errors errors = userServiceValidator.checkUserOkForUpdate(new User("  ", "test"));
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testUpdateWithNullUsername() {
        Errors errors = userServiceValidator.checkUserOkForUpdate(new User(null, "test"));
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }

    @Test
    public void testUpdateWithNullUser() {
        Errors errors = userServiceValidator.checkUserOkForUpdate(null);
        assertTrue(errors.hasError());
        assertEquals(1, errors.getErrorList().size());
    }
}

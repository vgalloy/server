package com.vgalloy.server.service.validator;

import com.vgalloy.server.StartServer;
import com.vgalloy.server.entity.User;
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
        Errors errors = userServiceValidator.checkUserOkForCreate(new User(null, "test", "test"));
        assertFalse(errors.hasError());
    }

    @Test
    public void testCreationWithNullUser() {
        Errors errors = userServiceValidator.checkUserOkForCreate(null);
        assertTrue(errors.hasError());
        assertEquals(errors.getErrorList().size(), 1);
    }

    @Test
    public void testUpdateOk() {
        Errors errors = userServiceValidator.checkUserOkForCreate(new User("1", "test", "test"));
        assertFalse(errors.hasError());
    }

    @Test
    public void testUpdateWithNullId() {
        Errors errors = userServiceValidator.checkUserOkForUpdate(new User(null, "test", "test"));
        assertTrue(errors.hasError());
        assertEquals(errors.getErrorList().size(), 1);
    }

    @Test
    public void testUpdateWithNullUser() {
        Errors errors = userServiceValidator.checkUserOkForUpdate(null);
        assertTrue(errors.hasError());
        assertEquals(errors.getErrorList().size(), 1);
    }
}

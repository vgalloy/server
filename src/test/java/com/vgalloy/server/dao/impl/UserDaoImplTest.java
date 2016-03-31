package com.vgalloy.server.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vgalloy.server.configuration.CommonConfiguration;
import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.exception.DaoException;
import com.vgalloy.server.model.entity.User;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CommonConfiguration.class)
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Before
    public void init() {
        userDao.removeAll();
    }

    @Test
    public void testCreateOk() {
        userDao.create(new User("user", "pass"));
        assertEquals(userDao.getById("user"), new User("user", "pass"));
    }

    @Test(expected = DaoException.class)
    public void testCreateWithNullUserName() {
        userDao.create(new User(null, "test"));
    }

    @Test(expected = DaoException.class)
    public void testCreateWithEmptyUserName() {
        userDao.create(new User(" ", "test"));
    }

    @Test
    public void testUpdateOk() {
        User user = new User("user", "pass");
        userDao.create(user);
        user.setPassword("password");
        userDao.update(user);
        assertEquals(user, userDao.getById("user"));
    }

    @Test(expected = DaoException.class)
    public void testUpdateWithNullUserName() {
        userDao.update(new User(null, "test"));
    }

    @Test(expected = DaoException.class)
    public void testUpdateWithEmptyUserName() {
        userDao.update(new User(" ", "test"));
    }

    @Test
    public void testGetOk() {
        User user = new User("user", "password");
        userDao.create(user);
        assertEquals(user, userDao.getById("user"));
    }

    @Test
    public void testGetNullId() {
        assertNull(userDao.getById(null));
    }

    @Test
    public void testGetEmptyId() {
        assertNull(userDao.getById("  "));
    }

    @Test
    public void testDeleteWithNullId() {
        try {
            userDao.deleteById(null);
        } catch (Exception e) {
            fail("Exception when try to delete with null id");
        }
    }

    @Test
    public void testDeleteWithNoExistingId() {
        try {
            userDao.deleteById("test");
        } catch (Exception e) {
            fail("Exception when try to delete with not existing id");
        }
    }

    @Test
    public void testDeleteWithCorrectId() {
        User user = new User("user", "password");
        userDao.create(user);
        userDao.deleteById(user.getId());
        assertEquals(0, userDao.getAll().size());
    }
}
package com.vgalloy.server.dao.impl;

import com.vgalloy.server.StartServer;
import com.vgalloy.server.dao.exception.DaoException;
import com.vgalloy.server.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StartServer.class, loader = SpringApplicationContextLoader.class)
public class UserDaoImplTest {

    @Autowired
    private UserDaoImpl userDao;

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
        User usertmp = userDao.getById("user");
        assertEquals(user, usertmp);
    }

    @Test
    public void testGetNullId() {
        User usertmp = userDao.getById(null);
        assertNull(usertmp);
    }

    @Test
    public void testGetEmptyId() {
        User usertmp = userDao.getById(null);
        assertNull(usertmp);
    }
}
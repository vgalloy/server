package com.vgalloy.server.service.impl;

import com.vgalloy.server.StartServer;
import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.service.exception.ServiceException;
import com.vgalloy.server.service.validator.UserServiceValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 11/12/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StartServer.class, loader = SpringApplicationContextLoader.class)
public class TestServiceUserServiceImpl {

    /**
     * /!\ @InjectMocks cherche à remplir TOUT les champs de l'objet ciblé avec des mocks ou des spys. Si certains
     * champs n'ont pas de spy/mock associé ils seront settés à null. Mockito n'ira pas chercher les beans de Spring.
     */

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private UserServiceValidator userServiceValidator;

    @Mock
    private UserDao userDao;

    private static final String ID = "1";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private User correctUser = new User(USERNAME, PASSWORD);
    private User nullUsernameUser = new User(" ", PASSWORD);
    private User emptyUsernameUser = new User(" ", PASSWORD);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        List<User> userList = new ArrayList<>();
        userList.add(correctUser);
        Mockito.when(userDao.getAll()).thenReturn(userList);
        Mockito.when(userDao.create(correctUser)).thenReturn(correctUser);
        Mockito.when(userDao.update(correctUser)).thenReturn(correctUser);
        Mockito.when(userDao.getById(USERNAME)).thenReturn(correctUser);
    }

    @Test
    public void testGetAll() {
        List<User> userList = new ArrayList<>();
        userList.add(correctUser);
        assertThat(userService.getAll(), is(userList));
    }

    @Test
    public void testCreateOk() {
        User user = userService.create(correctUser);
        assertEquals(correctUser, user);
    }


    @Test(expected = ServiceException.class)
    public void testCreateWithNullUsername() {
        userService.create(nullUsernameUser);
    }

    @Test(expected = ServiceException.class)
    public void testCreateWithEmptyUsername() {
        userService.create(emptyUsernameUser);
    }

    @Test(expected = ServiceException.class)
    public void testCreateWithNullUser() {
        userService.create(null);
    }

    @Test
    public void testGetByIdOk() {
        User user = userService.getByUsername(USERNAME);
        assertEquals(correctUser, user);
    }

    @Test(expected = ServiceException.class)
    public void testGetByWithNullUsername() {
        userService.getByUsername(null);
    }

    @Test(expected = ServiceException.class)
    public void testGetByWithEmptyUsername() {
        userService.getByUsername(" ");
    }

    @Test
    public void testUpdateOk() {
        userService.update(correctUser);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateWithNullUser() {
        userService.update(null);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateWithNullUsername() {
        userService.update(nullUsernameUser);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateWithEmptyUsername() {
        userService.update(emptyUsernameUser);
    }

    @Test
    public void testDeleteOk() {
        userService.deleteByUsername(ID);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteWithNullId() {
        userService.deleteByUsername(null);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteWithEmptyId() {
        userService.deleteByUsername(" ");
    }
}

package com.vgalloy.server.service.impl;

import com.vgalloy.server.StartServer;
import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.error.Errors;
import com.vgalloy.server.error.Error;
import com.vgalloy.server.service.exception.ServiceException;
import com.vgalloy.server.service.validator.UserServiceValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
    @Mock
    private UserDao personDao;
    @Mock
    private UserServiceValidator userServiceValidator;
    private static final String ID = "1";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "username";
    private User correctUser;
    private User nullIdUser;
    private User emptyIdUser;
    private User nullUsernameUser;
    private User emptyUsernameUser;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        List<User> userList = new ArrayList<>();
        correctUser = new User(ID, USERNAME, PASSWORD);
        nullIdUser = new User(null, USERNAME, PASSWORD);
        emptyIdUser = new User(" ", USERNAME, PASSWORD);
        nullUsernameUser = new User(ID, " ", PASSWORD);
        emptyUsernameUser = new User(ID, " ", PASSWORD);


        userList.add(correctUser);
        Mockito.when(personDao.getAll()).thenReturn(userList);
        Mockito.when(personDao.create(nullIdUser)).thenReturn(correctUser);
        Mockito.when(personDao.getById(ID)).thenReturn(correctUser);

        Mockito.when(userServiceValidator.checkUserOkForCreate(nullIdUser)).thenReturn(new Errors());
        Mockito.when(userServiceValidator.checkUserOkForCreate(null)).thenReturn(new Errors().addError(new Error("user : null")));
        Mockito.when(userServiceValidator.checkUserOkForCreate(emptyIdUser)).thenReturn(new Errors().addError(new Error("id : empty")));
        Mockito.when(userServiceValidator.checkUserOkForCreate(nullUsernameUser)).thenReturn(new Errors().addError(new Error("username : null")));
        Mockito.when(userServiceValidator.checkUserOkForCreate(emptyUsernameUser)).thenReturn(new Errors().addError(new Error("username : empty")));

        Mockito.when(userServiceValidator.checkIdOkForGet(ID)).thenReturn(new Errors());
        Mockito.when(userServiceValidator.checkIdOkForGet(null)).thenReturn(new Errors().addError(new Error("id : null")));
        Mockito.when(userServiceValidator.checkIdOkForGet(" ")).thenReturn(new Errors().addError(new Error("id : empty")));

        Mockito.when(userServiceValidator.checkUserOkForUpdate(correctUser)).thenReturn(new Errors());
        Mockito.when(userServiceValidator.checkUserOkForUpdate(nullIdUser)).thenReturn(new Errors().addError(new Error("id : null")));
        Mockito.when(userServiceValidator.checkUserOkForUpdate(emptyIdUser)).thenReturn(new Errors().addError(new Error("id : empty")));
        Mockito.when(userServiceValidator.checkUserOkForUpdate(nullUsernameUser)).thenReturn(new Errors().addError(new Error("username : null")));
        Mockito.when(userServiceValidator.checkUserOkForUpdate(emptyUsernameUser)).thenReturn(new Errors().addError(new Error("username : empty")));
        Mockito.when(userServiceValidator.checkUserOkForUpdate(null)).thenReturn(new Errors().addError(new Error("user : null")));

        Mockito.when(userServiceValidator.checkIdOkForDelete(ID)).thenReturn(new Errors());
        Mockito.when(userServiceValidator.checkIdOkForDelete(null)).thenReturn(new Errors().addError(new Error("id : null")));
        Mockito.when(userServiceValidator.checkIdOkForDelete(" ")).thenReturn(new Errors().addError(new Error("id : empty")));
    }

    @Test
    public void testGetAll() {
        List<User> userList = new ArrayList<>();
        userList.add(correctUser);
        assertThat(userService.getAll(), is(userList));
    }

    @Test
    public void testCreateOk() {
        User user = userService.create(nullIdUser);
        assertEquals(correctUser, user);
    }

    @Test(expected = ServiceException.class)
    public void testCreateWithEmptyId() {
        userService.create(emptyIdUser);
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
        User user = userService.getById(ID);
        assertEquals(correctUser, user);
    }

    @Test(expected = ServiceException.class)
    public void testGetByWithNullId() {
        userService.getById(null);
    }

    @Test(expected = ServiceException.class)
    public void testGetByWithEmptyId() {
        userService.getById(" ");
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
    public void testUpdateWithNullId() {
        userService.update(nullIdUser);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateWithEmptyId() {
        userService.update(emptyIdUser);
    }

    @Test
    public void testDeleteOk() {
        userService.deleteById(ID);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteWithNullId() {
        userService.deleteById(null);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteWithEmptyId() {
        userService.deleteById(" ");
    }
}

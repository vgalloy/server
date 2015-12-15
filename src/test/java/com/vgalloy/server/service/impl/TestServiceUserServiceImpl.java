package com.vgalloy.server.service.impl;

import com.vgalloy.server.StartServer;
import com.vgalloy.server.dao.GenericDao;
import com.vgalloy.server.entity.User;
import com.vgalloy.server.error.Error;
import com.vgalloy.server.error.Errors;
import com.vgalloy.server.service.exception.ServiceException;
import com.vgalloy.server.service.validator.UserServiceValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
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
    private GenericDao<User> personDao;

    @Mock
    private UserServiceValidator userServiceValidator;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        List<User> userList = new ArrayList<>();
        userList.add(new User("11", "n", "p"));
        Mockito.when(personDao.getAll()).thenReturn(userList);
        Mockito.when(personDao.create(any())).thenReturn(new User("23", "n", "p"));
        Mockito.when(personDao.getById("1")).thenReturn(new User("232", "n", "p"));


        Mockito.when(userServiceValidator.checkUserOkForCreate(any())).thenReturn(new Errors());
        Mockito.when(userServiceValidator.checkUserOkForCreate(null)).thenReturn(new Errors().addError(new Error("user : null")));

        Mockito.when(userServiceValidator.checkIdOkForGet("1")).thenReturn(new Errors());
        Mockito.when(userServiceValidator.checkIdOkForGet(null)).thenReturn(new Errors().addError(new Error("id : null")));
        Mockito.when(userServiceValidator.checkIdOkForGet(" ")).thenReturn(new Errors().addError(new Error("id : empty")));

        Mockito.when(userServiceValidator.checkUserOkForUpdate(new User("1", "n", "p"))).thenReturn(new Errors());
        Mockito.when(userServiceValidator.checkUserOkForUpdate(new User())).thenReturn(new Errors().addError(new Error("id : null")));
        Mockito.when(userServiceValidator.checkUserOkForUpdate(new User("", "n", "p"))).thenReturn(new Errors().addError(new Error("id : emptys")));
        Mockito.when(userServiceValidator.checkUserOkForUpdate(null)).thenReturn(new Errors().addError(new Error("user : null")));

        Mockito.when(userServiceValidator.checkIdOkForDelete(any())).thenReturn(new Errors());
        Mockito.when(userServiceValidator.checkIdOkForDelete(null)).thenReturn(new Errors().addError(new Error("id : null")));
        Mockito.when(userServiceValidator.checkIdOkForDelete(" ")).thenReturn(new Errors().addError(new Error("id : empty")));
    }

    @Test
    public void testGetAll() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("11", "n", "p"));
        assertThat(userService.getAll(), is(userList));
    }

    @Test
    public void testCreateOk() {
        User user = userService.create(new User("n", "p"));
        assertEquals(new User("23", "n", "p"), user);
    }

    @Test(expected = ServiceException.class)
    public void testCreateWithNullUser() {
        userService.create(null);
    }

    @Test
    public void testGetByIdOk() {
        User user = userService.getById("1");
        assertEquals(new User("232", "n", "p"), user);
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
        userService.update(new User("1", "n", "p"));
    }

    @Test(expected = ServiceException.class)
    public void testUpdateWithNullUser() {
        userService.update(null);
    }

    @Test(expected = ServiceException.class)
    public void testUpdateWithNullId() {
        userService.update(new User());
    }

    @Test(expected = ServiceException.class)
    public void testUpdateWithEmptyId() {
        userService.update(new User("", "n", "p"));
    }

    @Test
    public void testDeleteOk() {
        userService.delete("AZERTYH");
    }

    @Test(expected = ServiceException.class)
    public void testDeleteWithNullId() {
        userService.delete(null);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteWithEmptyId() {
        userService.delete(" ");
    }
}

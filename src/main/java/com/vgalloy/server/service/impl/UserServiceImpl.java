package com.vgalloy.server.service.impl;

import com.vgalloy.server.dao.GenericDao;
import com.vgalloy.server.entity.User;
import com.vgalloy.server.error.Errors;
import com.vgalloy.server.service.UserService;
import com.vgalloy.server.service.exception.ServiceException;
import com.vgalloy.server.service.validator.UserServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vincent Galloy on 09/12/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private GenericDao<User> personDao;
    @Autowired
    private UserServiceValidator userServiceValidator;

    @Override
    public List<User> getAll() {
        return personDao.getAll();
    }

    @Override
    public void create(User user) {
        Errors errors = userServiceValidator.checkUserOkForCreate(user);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        personDao.create(user);
    }

    @Override
    public User getById(String id) {
        Errors errors = userServiceValidator.checkIdOkForGet(id);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        return personDao.getById(id);
    }

    @Override
    public void update(User user) {
        Errors errors = userServiceValidator.checkUserOkForUpdate(user);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        personDao.update(user);
    }

    @Override
    public void delete(String id) {
        Errors errors = userServiceValidator.checkIdOkForDelete(id);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        personDao.delete(id);
    }
}

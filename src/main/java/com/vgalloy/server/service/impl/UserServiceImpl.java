package com.vgalloy.server.service.impl;

import com.vgalloy.server.dao.GenericDao;
import com.vgalloy.server.entity.User;
import com.vgalloy.server.error.Errors;
import com.vgalloy.server.logger.Log;
import com.vgalloy.server.logger.LogLevel;
import com.vgalloy.server.service.UserService;
import com.vgalloy.server.service.exception.ServiceException;
import com.vgalloy.server.service.validator.UserServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private GenericDao<User> personDao;
    @Autowired
    private UserServiceValidator userServiceValidator;

    @Override
    @Log(LogLevel.INFO)
    public List<User> getAll() {
        return personDao.getAll();
    }

    @Override
    @Log(LogLevel.INFO)
    public User create(User user) {
        Errors errors = userServiceValidator.checkUserOkForCreate(user);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        return personDao.create(user);
    }

    @Override
    @Log(LogLevel.INFO)
    public User getById(String id) {
        Errors errors = userServiceValidator.checkIdOkForGet(id);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        return personDao.getById(id);
    }

    @Override
    @Log(LogLevel.INFO)
    public User update(User user) {
        Errors errors = userServiceValidator.checkUserOkForUpdate(user);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        return personDao.update(user);
    }

    @Override
    @Log(LogLevel.INFO)
    public void delete(String id) {
        Errors errors = userServiceValidator.checkIdOkForDelete(id);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        personDao.delete(id);
    }
}

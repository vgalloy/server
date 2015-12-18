package com.vgalloy.server.service.impl;

import com.vgalloy.server.aspect.logger.Log;
import com.vgalloy.server.aspect.logger.LogLevel;
import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.error.Errors;
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
@Log(LogLevel.DEBUG)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao personDao;
    @Autowired
    private UserServiceValidator userServiceValidator;

    @Override
    public List<User> getAll() {
        return personDao.getAll();
    }

    @Override
    public User create(User user) {
        Errors errors = userServiceValidator.checkUserOkForCreate(user);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        return personDao.create(user);
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
    public User update(User user) {
        Errors errors = userServiceValidator.checkUserOkForUpdate(user);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        return personDao.update(user);
    }

    @Override
    public void deleteById(String id) {
        Errors errors = userServiceValidator.checkIdOkForDelete(id);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        personDao.deleteById(id);
    }
}

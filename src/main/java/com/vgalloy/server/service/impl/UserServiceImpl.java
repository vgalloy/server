package com.vgalloy.server.service.impl;

import com.vgalloy.server.aspect.logger.Log;
import com.vgalloy.server.aspect.logger.LogLevel;
import com.vgalloy.server.aspect.security.Security;
import com.vgalloy.server.aspect.security.SecurityException;
import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.error.Errors;
import com.vgalloy.server.service.UserService;
import com.vgalloy.server.service.exception.ServiceException;
import com.vgalloy.server.service.validator.UserServiceSecurityValidator;
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
    private UserDao userDao;
    @Autowired
    private UserServiceValidator userServiceValidator;
    @Autowired
    private UserServiceSecurityValidator userServiceSecurityValidator;

    @Override
    @Security(SecurityLevel.ADMIN)
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    @Security(SecurityLevel.ADMIN)
    public User createOrUpdate(User user) {
        Errors errors = userServiceValidator.checkCreateOrUpdate(user);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        return userDao.update(user);
    }

    @Override
    @Security({SecurityLevel.ADMIN, SecurityLevel.USER})
    public User getByUsername(String username) {
        if (!userServiceSecurityValidator.isGetOk(username)) {
            throw new SecurityException(SecurityException.UNAUTHORIZED);
        }
        Errors errors = userServiceValidator.checkGet(username);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        return userDao.getById(username);
    }

    @Override
    @Security({SecurityLevel.ADMIN, SecurityLevel.USER})
    public User changePassword(String username, String newPassword) {
        if (!userServiceSecurityValidator.isChangePasswordOk(username)) {
            throw new SecurityException(SecurityException.UNAUTHORIZED);
        }
        User user = getByUsername(username);
        Errors errors = userServiceValidator.checkChangePassword(user, newPassword);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        user.setPassword(newPassword);
        return userDao.update(user);
    }

    @Override
    @Security(SecurityLevel.ADMIN)
    public User changeRole(String username, SecurityLevel role) {
        User user = getByUsername(username);
        Errors errors = userServiceValidator.checkChangeRole(user, role);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        user.setRole(role);
        return userDao.update(user);
    }

    @Override
    @Security(SecurityLevel.ADMIN)
    public void deleteByUsername(String username) {
        Errors errors = userServiceValidator.checkDelete(username);
        if (errors.hasError()) {
            throw new ServiceException(errors);
        }
        userDao.deleteById(username);
    }
}

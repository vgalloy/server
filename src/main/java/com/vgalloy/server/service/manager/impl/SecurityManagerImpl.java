package com.vgalloy.server.service.manager.impl;

import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.model.entity.User;
import com.vgalloy.server.service.manager.SecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
@Service
public class SecurityManagerImpl implements SecurityManager {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean checkUsernameAndPassword(String username, String password) {
        return getUserWithUsernameAndPassword(username, password) != null;
    }

    @Override
    public User getUserWithUsernameAndPassword(String username, String password) {
        User user;
        if (username == null || username.trim().isEmpty()) {
            return null;
        } else {
            user = userDao.getById(username);
            if (user == null) {
                return null;
            } else if ((user.getPassword() != null && user.getPassword().equals(password)) || (user.getPassword() == null && password == null)) {
                return user;
            } else {
                return null;
            }
        }
    }
}

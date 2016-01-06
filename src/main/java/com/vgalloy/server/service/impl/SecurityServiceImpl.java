package com.vgalloy.server.service.impl;

import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean checkUsernameAndPassword(String username, String password) {
        return getUserWithPassword(username, password) != null;
    }

    @Override
    public User getUserWithPassword(String username, String password) {
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

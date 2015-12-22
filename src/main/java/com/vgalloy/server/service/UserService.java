package com.vgalloy.server.service;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.model.entity.User;

import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
public interface UserService {
    List<User> getAll();

    User createOrUpdate(User user);

    User getByUsername(String id);

    User changePassword(String username, String password);

    User changeRole(String username, SecurityLevel securityLevel);

    void deleteByUsername(String id);
}

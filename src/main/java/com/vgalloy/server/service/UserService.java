package com.vgalloy.server.service;

import com.vgalloy.server.entity.User;

import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
public interface UserService {
    List<User> getAll();

    User create(User user);

    User getById(String id);

    User update(User user);

    void delete(String id);
}

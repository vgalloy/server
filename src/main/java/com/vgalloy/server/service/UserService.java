package com.vgalloy.server.service;

import com.vgalloy.server.entity.User;

import java.util.List;

/**
 * Created by Vincent Galloy on 09/12/15.
 */
public interface UserService {
    List<User> getAll();

    void create(User user);

    User getById(String id);

    void update(User user);

    void delete(String id);
}

package com.vgalloy.server.webservice;

import com.vgalloy.server.entity.User;

import java.util.List;

/**
 * Created by Vincent Galloy on 09/12/15.
 */
public interface UserWebService {
    List<User> getAll();

    User create(User user);

    User getById(String id);

    User update(User user);

    void delete(String id);
}

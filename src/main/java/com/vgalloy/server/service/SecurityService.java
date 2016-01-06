package com.vgalloy.server.service;

import com.vgalloy.server.dao.model.entity.User;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
public interface SecurityService {
    boolean checkUsernameAndPassword(String username, String password);

    User getUserWithPassword(String username, String password);
}

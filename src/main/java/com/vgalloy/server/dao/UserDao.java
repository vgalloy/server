package com.vgalloy.server.dao;

import com.vgalloy.server.dao.model.entity.User;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 18/12/15.
 */
public interface UserDao extends GenericDao<User>{

    User getByUsername(String username);

    void deleteByUsername(String username);
}

package com.vgalloy.server.webservice;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.webservice.dto.UserDto;

import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
public interface UserWebService {
    List<User> getAll();

    User create(String username, UserDto userDto);

    User getByUsername(String username);

    User changePassword(String username, String password);

    User changeRole(String username, SecurityLevel securityLevel);

    void delete(String username);
}

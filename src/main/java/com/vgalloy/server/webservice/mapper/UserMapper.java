package com.vgalloy.server.webservice.mapper;

import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.webservice.dto.UserDto;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
public interface UserMapper {
    static User map(UserDto userDto) {
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }
}

package com.vgalloy.server.webservice.mapper;

import java.util.Collections;
import java.util.List;

import com.vgalloy.server.model.entity.User;
import com.vgalloy.server.webservice.dto.UserDto;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
public interface UserMapper {

    /**
     * Map an userDto into user.
     *
     * @param username The username
     * @param userDto  The userDto
     * @return The user
     */
    static User map(String username, UserDto userDto) {
        return new User(username, userDto.getPassword(), userDto.getRole());
    }
}

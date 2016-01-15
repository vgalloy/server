package com.vgalloy.server.webservice.mapper;

import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.webservice.dto.UserDto;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
public interface UserMapper {
    /**
     * Transforme un userDto en user.
     *
     * @param username Le nom d'utilisateur
     * @param userDto  Le user Dto à transformer
     * @return Le user
     */
    static User map(String username, UserDto userDto) {
        return new User(username, userDto.getPassword(), userDto.getRole());
    }
}

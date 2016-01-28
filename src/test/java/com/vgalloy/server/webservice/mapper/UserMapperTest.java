package com.vgalloy.server.webservice.mapper;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.model.entity.User;
import com.vgalloy.server.webservice.dto.UserDto;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
public class UserMapperTest {
    @Test
    public void testMap1() {
        UserDto userDto = new UserDto();
        userDto.setPassword("pass");
        userDto.setRole(SecurityLevel.ADMIN);

        User user = UserMapper.map("test", userDto);

        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getUsername(), "test");
        assertEquals(user.getRole(), SecurityLevel.ADMIN);
    }

    @Test
    public void testMap2() {
        UserDto userDto = new UserDto();
        userDto.setPassword(null);
        userDto.setRole(null);

        User user = UserMapper.map(null, userDto);

        assertEquals(user.getPassword(), null);
        assertEquals(user.getUsername(), null);
        assertEquals(user.getRole(), null);
    }
}
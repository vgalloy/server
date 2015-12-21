package com.vgalloy.server.webservice.impl;

import com.vgalloy.server.aspect.security.Security;
import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.service.UserService;
import com.vgalloy.server.webservice.UserWebService;
import com.vgalloy.server.webservice.dto.UserDto;
import com.vgalloy.server.webservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
@RestController
@RequestMapping("/user")
public class UserWebServiceImpl implements UserWebService {
    @Autowired
    private UserService userService;

    @Override
    @Security(SecurityLevel.ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @Override
    @Security(SecurityLevel.ADMIN)
    @RequestMapping(value="/{username}", method = RequestMethod.POST)
    public User create(@PathVariable String username, @RequestBody UserDto userDto) {
        User user = UserMapper.map(userDto);
        user.setUsername(username);
        return userService.create(user);
    }

    @Override
    @Security(SecurityLevel.USER)
    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public User getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @Override
    @Security(SecurityLevel.USER)
    @RequestMapping(value="/{username}", method = RequestMethod.PUT)
    public User update(@PathVariable String username, @RequestBody UserDto userDto) {
        User user = UserMapper.map(userDto);
        user.setUsername(username);
        return userService.update(user);
    }

    @Override
    @Security(SecurityLevel.ADMIN)
    @RequestMapping(value="/{username}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String username) {
        userService.deleteByUsername(username);
    }
}

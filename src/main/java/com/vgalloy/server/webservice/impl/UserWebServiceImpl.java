package com.vgalloy.server.webservice.impl;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.model.entity.User;
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
@RequestMapping("user")
public class UserWebServiceImpl implements UserWebService {

    @Autowired
    private UserService userService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        return userService.getAll();
    }

    @Override
    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public User create(@PathVariable String username, @RequestBody UserDto userDto) {
        return userService.createOrUpdate(UserMapper.map(username, userDto));
    }

    @Override
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @Override
    @RequestMapping(value = "/{username}/changePassword", method = RequestMethod.PUT)
    public User changePassword(@PathVariable String username, @RequestBody String newPassword) {
        return userService.changePassword(username, newPassword);
    }

    @Override
    @RequestMapping(value = "/{username}/changeRole", method = RequestMethod.PUT)
    public User changeRole(@PathVariable String username, @RequestBody SecurityLevel securityLevel) {
        return userService.changeRole(username, securityLevel);
    }

    @Override
    @RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
    public void deleteByUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
    }
}

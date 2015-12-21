package com.vgalloy.server.webservice.impl;

import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.service.UserService;
import com.vgalloy.server.webservice.UserWebService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping("/getAll")
    public List<User> getAll() {
        return userService.getAll();
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public User getById(@RequestBody String id) {
        return userService.getByUsername(id);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestBody String id) {
        userService.deleteByUsername(id);
    }
}

package com.vgalloy.server.webservice.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 18/12/15.
 */
@RestController
@RequestMapping("home")
public class HomeWebService {
    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "Server is running !";
    }
}

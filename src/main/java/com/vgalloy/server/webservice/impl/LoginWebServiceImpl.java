package com.vgalloy.server.webservice.impl;

import com.vgalloy.server.service.SecurityService;
import com.vgalloy.server.webservice.LoginWebService;
import com.vgalloy.server.webservice.dto.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
@RestController
@RequestMapping("/login")
public class LoginWebServiceImpl implements LoginWebService {
    @Autowired
    private SecurityService securityService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public boolean checkPassword(@RequestBody AuthenticationDTO authenticationDTO) {
        return securityService.checkUsernameAndPassword(authenticationDTO.getUsername(), authenticationDTO.getPassword());
    }
}

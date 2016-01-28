package com.vgalloy.server.webservice.impl;

import com.vgalloy.server.service.CredentialService;
import com.vgalloy.server.service.SecurityService;
import com.vgalloy.server.webservice.LoginWebService;
import com.vgalloy.server.webservice.dto.AuthenticationDto;
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
@RequestMapping("security")
public class LoginWebServiceImpl implements LoginWebService {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private CredentialService credentialService;

    @Override
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public boolean checkPassword(@RequestBody AuthenticationDto authenticationDto) {
        return securityService.checkUsernameAndPassword(authenticationDto.getUsername(), authenticationDto.getPassword());
    }

    @Override
    @RequestMapping(value = "url", method = RequestMethod.GET)
    public String getGoogleTokenUrl() {
        return credentialService.generateUrl();
    }

    @Override
    @RequestMapping(value = "token", method = RequestMethod.POST)
    public boolean setToken(@RequestBody String tokenWithoutSlash) {
        String correctToken = tokenWithoutSlash.replace("%2F", "/");
        return credentialService.setToken(correctToken);
    }
}

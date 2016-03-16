package com.vgalloy.server.service.impl;

import com.vgalloy.server.aspect.security.Security;
import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.service.manager.CredentialManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 28/01/16.
 */
@Service
public class CredentialServiceImpl implements com.vgalloy.server.service.CredentialService {

    @Autowired
    private CredentialManager credentialManager;

    @Override
    @Security({SecurityLevel.ADMIN})
    public void setToken(String token) {
        credentialManager.setToken(token);
    }

    @Override
    @Security({SecurityLevel.ADMIN})
    public String generateGoogleTokenUrl() {
        return credentialManager.generateGoogleTokenUrl();
    }
}

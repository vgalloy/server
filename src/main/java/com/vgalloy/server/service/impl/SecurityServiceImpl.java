package com.vgalloy.server.service.impl;

import com.vgalloy.server.service.manager.SecurityManager;
import com.vgalloy.server.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 28/01/16.
 */
@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private SecurityManager securityManager;

    @Override
    public boolean checkUsernameAndPassword(String username, String password) {
        return securityManager.checkUsernameAndPassword(username, password);
    }
}

package com.vgalloy.server.service.validator;

import com.vgalloy.server.aspect.security.SecurityApi;
import com.vgalloy.server.aspect.security.SecurityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 22/12/15.
 */
@Component
public class UserServiceSecurityValidator {
    @Autowired
    private SecurityApi securityApi;

    public boolean isGetOk(String username) {
        return canModify(username);
    }

    public boolean isChangePasswordOk(String username) {
        return canModify(username);
    }

    private boolean canModify(String username) {
        if(SecurityLevel.ADMIN.equals(securityApi.getCurrentUserRole())){
            return  true;
        } else if (SecurityLevel.USER.equals(securityApi.getCurrentUserRole())){
            if(securityApi.getCurrentUsername() != null && securityApi.getCurrentUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

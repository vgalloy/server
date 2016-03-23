package com.vgalloy.server.service.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vgalloy.server.aspect.security.SecurityApi;
import com.vgalloy.server.aspect.security.SecurityLevel;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 22/12/15.
 */
@Component
public class UserServiceSecurityValidator {

    @Autowired
    private SecurityApi securityApi;

    /**
     * Can the current user get information about another user.
     *
     * @param username The username of the user we would retrieve information.
     * @return true if the current user can access the  information
     */
    public boolean isGetOk(String username) {
        return canModify(username);
    }

    /**
     * Can the current user change the password of another.
     *
     * @param username The username of the user we would change password.
     * @return true if current user can change password.
     */
    public boolean isChangePasswordOk(String username) {
        return canModify(username);
    }

    /**
     * Can the current user modify another user.
     *
     * @param username The username of the user we would modify information.
     * @return true if current user can modify user.
     */
    private boolean canModify(String username) {
        if (SecurityLevel.ADMIN.equals(securityApi.getCurrentUserRole())) {
            return true;
        }
        if (SecurityLevel.USER.equals(securityApi.getCurrentUserRole())) {
            if (securityApi.getCurrentUsername() != null && securityApi.getCurrentUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

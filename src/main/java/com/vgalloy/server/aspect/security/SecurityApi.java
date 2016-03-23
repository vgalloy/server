package com.vgalloy.server.aspect.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vgalloy.server.model.entity.User;
import com.vgalloy.server.service.manager.SecurityManager;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 15/12/15.
 */
@Component
public class SecurityApi {

    private final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    @Autowired
    private SecurityManager securityManager;

    /**
     * Set the user corresponding to the username/password.
     *
     * @param username The username
     * @param password The password
     */
    public void setUser(String username, String password) {
        userThreadLocal.set(securityManager.getUserWithUsernameAndPassword(username, password));
    }

    /**
     * Get the current user's security level.
     *
     * @return The securityLevel or null is no one is define
     */
    public SecurityLevel getCurrentUserRole() {
        User user = userThreadLocal.get();
        if (user == null) {
            return SecurityLevel.ANONYMOUS;
        } else {
            return user.getRole();
        }
    }

    /**
     * Get the current user's username.
     *
     * @return The username
     */
    public String getCurrentUsername() {
        User user = userThreadLocal.get();
        if (user == null) {
            return null;
        } else {
            return user.getUsername();
        }
    }
}

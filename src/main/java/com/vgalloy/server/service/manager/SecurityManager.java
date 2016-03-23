package com.vgalloy.server.service.manager;

import com.vgalloy.server.model.entity.User;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
public interface SecurityManager {

    /**
     * Check if username/password is correct.
     *
     * @param username The username
     * @param password The password
     * @return true if username match with password
     */
    boolean checkUsernameAndPassword(String username, String password);

    /**
     * Get the user define with the given username and password.
     *
     * @param username The username
     * @param password The password
     * @return The corresponding user or null
     */
    User getUserWithUsernameAndPassword(String username, String password);
}

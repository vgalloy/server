package com.vgalloy.server.service;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 28/01/16.
 */
public interface SecurityService {

    /**
     * Check if username/password is correct.
     *
     * @param username The username
     * @param password The password
     * @return true if username match with password
     */
    boolean checkUsernameAndPassword(String username, String password);
}

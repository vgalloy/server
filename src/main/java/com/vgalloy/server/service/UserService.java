package com.vgalloy.server.service;

import java.util.List;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.model.entity.User;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
public interface UserService {

    /**
     * Get all users.
     *
     * @return All users as a list.
     */
    List<User> getAll();

    /**
     * Create or update user.
     *
     * @param user The user
     * @return Updated user
     */
    User createOrUpdate(User user);

    /**
     * Get a user with his username.
     *
     * @param username The username
     * @return The user
     */
    User getByUsername(String username);

    /**
     * Change the password.
     *
     * @param username    The username
     * @param newPassword The new password
     * @return The modify user
     */
    User changePassword(String username, String newPassword);

    /**
     * Change the security rules for a user.
     *
     * @param username      The username
     * @param securityLevel The new rules for the user
     * @return The modify user
     */
    User changeRole(String username, SecurityLevel securityLevel);

    /**
     * Delete a user.
     *
     * @param username The username
     */
    void deleteByUsername(String username);
}

package com.vgalloy.server.service.validator;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.model.entity.User;
import com.vgalloy.server.service.error.Error;
import com.vgalloy.server.service.error.Errors;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 10/12/15.
 */
@Component
public class UserServiceValidator {

    /**
     * Check user if ok for create or update.
     *
     * @param user The user to test
     * @return Errors object with the error
     */
    public Errors checkCreateOrUpdate(User user) {
        Errors errors = new Errors();

        isUserNotNull(errors, user);
        if (!errors.hasError()) {
            isUsernameNotNullAndNotEmpty(errors, user.getUsername());
            isPasswordNotNullAndNotEmpty(errors, user.getPassword());
        }

        return errors;
    }

    /**
     * Check username is correct for get method.
     *
     * @param username The username to test
     * @return Errors object with the error
     */
    public Errors checkGet(String username) {
        Errors errors = new Errors();

        isUsernameNotNullAndNotEmpty(errors, username);

        return errors;
    }

    /**
     * Check username is correct for delete method.
     *
     * @param username The username to test
     * @return Errors object with the error
     */
    public Errors checkDelete(String username) {
        Errors errors = new Errors();

        isUsernameNotNullAndNotEmpty(errors, username);

        return errors;
    }

    /**
     * Check user and new password ok for change password method.
     *
     * @param user The user to test
     * @param password The new password
     * @return Errors object with the error
     */
    public Errors checkChangePassword(User user, String password) {
        Errors errors = new Errors();

        isUserNotNull(errors, user);
        if (!errors.hasError()) {
            isPasswordNotNullAndNotEmpty(errors, password);
        }

        return errors;
    }

    /**
     * Check user and security level are for changeRole method.
     *
     * @param user The user to test
     * @param securityLevel The new security level
     * @return Errors object with the error
     */
    public Errors checkChangeRole(User user, SecurityLevel securityLevel) {
        Errors errors = new Errors();

        isUserNotNull(errors, user);
        isSecurityLevelNotNull(errors, securityLevel);

        return errors;
    }

    /**
     * Check user not null.
     *
     * @param errors object with the error
     * @param user The user to test
     */
    private static void isUserNotNull(Errors errors, User user) {
        if (user == null) {
            errors.addError(new Error("user : null"));
        }
    }

    /**
     * Check username not null and not empty.
     *
     * @param errors object with the error
     * @param username The username to test
     */
    private static void isUsernameNotNullAndNotEmpty(Errors errors, String username) {
        if (username == null) {
            errors.addError(new Error("username : null"));
        } else if (username.trim().isEmpty()) {
            errors.addError(new Error("username : empty"));
        }
    }

    /**
     * Check password is not null and not empty.
     *
     * @param errors object with the error
     * @param password The password to test
     */
    private static void isPasswordNotNullAndNotEmpty(Errors errors, String password) {
        if (password == null) {
            errors.addError(new Error("password : null"));
        } else if (password.trim().isEmpty()) {
            errors.addError(new Error("password : empty"));
        }
    }

    /**
     * Check role is not null.
     *
     * @param errors object with the error
     * @param securityLevel The role to test
     */
    private static void isSecurityLevelNotNull(Errors errors, SecurityLevel securityLevel) {
        if (securityLevel == null) {
            errors.addError(new Error("securityLevel : null"));
        }
    }
}

package com.vgalloy.server.service.validator;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.error.Error;
import com.vgalloy.server.error.Errors;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 10/12/15.
 */
@Component
public class UserServiceValidator {
    public Errors checkCreateOrUpdate(User user) {
        Errors errors = new Errors();

        isUserNotNull(errors, user);
        if (!errors.hasError()) {
            isUsernameNotNullAndNotEmpty(errors, user.getUsername());
            isPasswordNotNullAndNotEmpty(errors, user.getPassword());
        }

        return errors;
    }

    public Errors checkGet(String username) {
        Errors errors = new Errors();

        isUsernameNotNullAndNotEmpty(errors, username);

        return errors;
    }

    public Errors checkDelete(String username) {
        Errors errors = new Errors();

        isUsernameNotNullAndNotEmpty(errors, username);

        return errors;
    }

    public Errors checkChangePassword(User user, String password) {
        Errors errors = new Errors();

        isUserNotNull(errors, user);
        if (!errors.hasError()) {
            isPasswordNotNullAndNotEmpty(errors, password);
        }

        return errors;
    }

    public Errors checkChangeRole(User user, SecurityLevel securityLevel) {
        Errors errors = new Errors();

        isUserNotNull(errors, user);
        isSecurityLevelNotNull(errors, securityLevel);

        return errors;
    }

    private static void isUserNotNull(Errors errors, User user) {
        if (user == null) {
            errors.addError(new Error("user : null"));
        }
    }

    private static void isUsernameNotNullAndNotEmpty(Errors errors, String username) {
        if (username == null) {
            errors.addError(new Error("username : null"));
        } else if (username.trim().isEmpty()) {
            errors.addError(new Error("username : empty"));
        }
    }

    private static void isPasswordNotNullAndNotEmpty(Errors errors, String password) {
        if (password == null) {
            errors.addError(new Error("password : null"));
        } else if (password.trim().isEmpty()) {
            errors.addError(new Error("password : empty"));
        }
    }

    private static void isSecurityLevelNotNull(Errors errors, SecurityLevel securityLevel) {
        if (securityLevel == null) {
            errors.addError(new Error("securityLevel : null"));
        }
    }
}

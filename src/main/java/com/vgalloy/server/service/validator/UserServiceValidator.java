package com.vgalloy.server.service.validator;

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
    public Errors checkUserOkForCreate(User user) {
        Errors errors = new Errors();

        isUserNotNull(errors, user);
        if (!errors.hasError()) {
            isUsernameNotNullAndNotEmpty(errors, user.getUsername());
        }

        return errors;
    }

    public Errors checkIdOkForGet(String username) {
        Errors errors = new Errors();

        isUsernameNotNullAndNotEmpty(errors, username);

        return errors;
    }

    public Errors checkUserOkForUpdate(User user) {
        Errors errors = new Errors();

        isUserNotNull(errors, user);
        if (!errors.hasError()) {
            isUsernameNotNullAndNotEmpty(errors, user.getUsername());
        }

        return errors;
    }

    public Errors checkUsernameOkForDelete(String username) {
        Errors errors = new Errors();

        isUsernameNotNullAndNotEmpty(errors, username);

        return errors;
    }

    private void isUserNotNull(Errors errors, User user) {
        if (user == null) {
            errors.addError(new Error("user : null"));
        }
    }

    private void isUsernameNotNullAndNotEmpty(Errors errors, String username) {
        if (username == null) {
            errors.addError(new Error("username : null"));
        } else if (username.trim().isEmpty()) {
            errors.addError(new Error("username : empty"));
        }
    }


}

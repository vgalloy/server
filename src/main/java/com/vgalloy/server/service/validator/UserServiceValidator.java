package com.vgalloy.server.service.validator;

import com.vgalloy.server.entity.User;
import com.vgalloy.server.error.Error;
import com.vgalloy.server.error.Errors;
import org.springframework.stereotype.Component;

/**
 * Created by Vincent Galloy on 10/12/15.
 */
@Component
public class UserServiceValidator {
    public Errors checkUserOkForUpdate(User user) {
        Errors errors = new Errors();

        isUserNotNull(errors, user);
        isIdNotNull(errors, user.getId());

        return errors;
    }

    public Errors checkIdOkForDelete(String id) {
        Errors errors = new Errors();

        isIdNotNull(errors, id);

        return errors;
    }

    public Errors checkIdOkForGet(String id) {
        Errors errors = new Errors();

        isIdNotNull(errors, id);

        return errors;
    }

    public Errors checkUserOkForCreate(User user) {
        Errors errors = new Errors();
        isUserNotNull(errors, user);
        return errors;
    }

    private void isUserNotNull(Errors errors, User user) {
        if (user == null) {
            errors.addError(new Error("User : null"));
        }
    }

    private void isIdNotNull(Errors errors, String id) {
        if (id == null) {
            errors.addError(new Error("id : null"));
        }
    }
}

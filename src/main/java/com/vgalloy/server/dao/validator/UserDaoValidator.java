package com.vgalloy.server.dao.validator;

import com.vgalloy.server.model.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
@Component
public class UserDaoValidator {

    /**
     * Check user is ok for create and update method.
     *
     * @param user The user to test
     * @return true the user is ok for create and update method
     */
    public boolean isUserOkForCreateOrUpdate(User user) {
        return CommonValidator.isReferableOkForCreateOrUpdate(user);
    }
}


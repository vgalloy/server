package com.vgalloy.server.dao.validator;

import com.vgalloy.server.model.Referable;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 07/03/16.
 */
public final class CommonValidator {

    /**
     * Default constructor to prevent instantiation.
     */
    private CommonValidator() {
        super();
    }

    /**
     * Check if referable object is not null and get a correct id.
     * @param referable The referable
     * @return true if the object is correct
     */
    public static boolean isReferableOkForCreateOrUpdate(Referable referable) {
        return referable != null && referable.getId() != null && !referable.getId().trim().isEmpty();
    }
}

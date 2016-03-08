package com.vgalloy.server.dao.validator;

import com.vgalloy.server.model.entity.Description;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 07/03/16.
 */
@Component
public class DescriptionDaoValidator {

    /**
     * Is description ok for create or update.
     *
     * @param description Description to check
     * @return true if description is ok
     */
    public boolean isDescriptionOkForCreateOrUpdate(Description description) {
        return CommonValidator.isReferableOkForCreateOrUpdate(description);
    }
}

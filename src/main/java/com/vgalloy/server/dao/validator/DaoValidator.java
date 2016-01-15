package com.vgalloy.server.dao.validator;

import com.vgalloy.server.dao.model.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
@Component
public class DaoValidator {
    /**
     * Permet de s'assurer que l'utilisateur est conforme au spécification.
     *
     * @param user L'utilistateur à verifier
     * @return true si aucun problème n'est détécté. Faux sinon.
     */
    public boolean isUserOkForCreateOrUpdate(User user) {
        return user != null && user.getUsername() != null && !user.getUsername().trim().isEmpty();
    }
}


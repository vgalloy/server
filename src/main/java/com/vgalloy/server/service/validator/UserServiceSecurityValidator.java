package com.vgalloy.server.service.validator;

import com.vgalloy.server.aspect.security.SecurityApi;
import com.vgalloy.server.aspect.security.SecurityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 22/12/15.
 */
@Component
public class UserServiceSecurityValidator {

    @Autowired
    private SecurityApi securityApi;

    /**
     * Assure que l'utilisateur courant peut obtenir les informations sur l'utilisateur dont le nom est passé en
     * paramètre.
     *
     * @param username Le nom d'utilisateur que l'on chercher à obtenir
     * @return true si l'utilisateur courant peux voir l'utilisateur spécifié.
     */
    public boolean isGetOk(String username) {
        return canModify(username);
    }

    /**
     * Assure que l'utilisateur courant peut modifier le mot de passe de l'utilisateur dont le nom est passé en
     * paramètre.
     *
     * @param username Le nom d'utilisateur que l'on chercher à obtenir
     * @return true si l'utilisateur courant peux le mot de passe de l'utilisateur spécifié.
     */
    public boolean isChangePasswordOk(String username) {
        return canModify(username);
    }

    /**
     * Assure que l'utilisateur courant peut obtenir/modifier les informations sur l'utilisateur dont le nom est passé en
     * paramètre.
     *
     * @param username Le nom d'utilisateur que l'on chercher à obtenir
     * @return true si l'utilisateur courant peux voir/modifier l'utilisateur spécifié.
     */
    private boolean canModify(String username) {
        if (SecurityLevel.ADMIN.equals(securityApi.getCurrentUserRole())) {
            return true;
        } else if (SecurityLevel.USER.equals(securityApi.getCurrentUserRole())) {
            if (securityApi.getCurrentUsername() != null && securityApi.getCurrentUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

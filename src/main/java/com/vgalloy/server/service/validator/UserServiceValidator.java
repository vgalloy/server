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
     * S'assure que l'utilisateur est correct pour la création ou la modification.
     *
     * @param user L'utilisateur à tester
     * @return L'objet Errors contenant les eventuelles erreurs
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
     * S'assure que le nom d'utilisateur est correct pour l'obtention d'un utilisateur.
     *
     * @param username Le nom d'utilisateur à tester
     * @return L'objet Errors contenant les eventuelles erreurs
     */
    public Errors checkGet(String username) {
        Errors errors = new Errors();

        isUsernameNotNullAndNotEmpty(errors, username);

        return errors;
    }

    /**
     * S'assure que le nom d'utilisateur est correct pour la suppression.
     *
     * @param username Le nom d'utilisateur à tester
     * @return L'objet Errors contenant les eventuelles erreurs
     */
    public Errors checkDelete(String username) {
        Errors errors = new Errors();

        isUsernameNotNullAndNotEmpty(errors, username);

        return errors;
    }

    /**
     * S'assure que l'utilisateur et son mot de passe sont corrects pour la modification.
     *
     * @param user     L'utilisateur à tester
     * @param password Le nouveau mot de passe
     * @return L'objet Errors contenant les eventuelles erreurs
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
     * S'assure que l'utilisateur et son role sont corrects pour la modification.
     *
     * @param user          L'utilisateur à tester
     * @param securityLevel Le nouveau role
     * @return L'objet Errors contenant les eventuelles erreurs
     */
    public Errors checkChangeRole(User user, SecurityLevel securityLevel) {
        Errors errors = new Errors();

        isUserNotNull(errors, user);
        isSecurityLevelNotNull(errors, securityLevel);

        return errors;
    }

    /**
     * S'assure que l'utilisateur est non null.
     *
     * @param errors L'objet Errors contenant les eventuelles erreurs
     * @param user   L'utilisateur à tester
     */
    private static void isUserNotNull(Errors errors, User user) {
        if (user == null) {
            errors.addError(new Error("user : null"));
        }
    }

    /**
     * S'assure que le nom d'utilisateur est non null et non vide.
     *
     * @param errors   L'objet Errors contenant les eventuelles erreurs
     * @param username Le nom d'utilisateur à tester
     */
    private static void isUsernameNotNullAndNotEmpty(Errors errors, String username) {
        if (username == null) {
            errors.addError(new Error("username : null"));
        } else if (username.trim().isEmpty()) {
            errors.addError(new Error("username : empty"));
        }
    }

    /**
     * S'assure que le mot de passe est non null et non vide.
     *
     * @param errors   L'objet Errors contenant les eventuelles erreurs
     * @param password Le mot de passe à tester
     */
    private static void isPasswordNotNullAndNotEmpty(Errors errors, String password) {
        if (password == null) {
            errors.addError(new Error("password : null"));
        } else if (password.trim().isEmpty()) {
            errors.addError(new Error("password : empty"));
        }
    }

    /**
     * S'assure que le role est non null.
     *
     * @param errors        L'objet Errors contenant les eventuelles erreurs
     * @param securityLevel Le role à tester
     */
    private static void isSecurityLevelNotNull(Errors errors, SecurityLevel securityLevel) {
        if (securityLevel == null) {
            errors.addError(new Error("securityLevel : null"));
        }
    }
}

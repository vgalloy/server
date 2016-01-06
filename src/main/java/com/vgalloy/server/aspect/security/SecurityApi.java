package com.vgalloy.server.aspect.security;

import com.vgalloy.server.dao.model.entity.User;
import com.vgalloy.server.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 15/12/15.
 */
@Component
public class SecurityApi {
    private ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    @Autowired
    private SecurityService securityService;

    /**
     * Cette méthode test si la combinaison nom d'utilisateur et mot de passe est correcte.
     * Si cette combinaison est correcte, les droits de l'utilisateur seront disponibles partout dans le tread lié à la
     * requête.
     * Si la combinaison est incorrecte (par exemple en cas d'utilisateur anonyme) aucun droit ne sera disponible
     *
     * @param username Le nom d'utilisateur de la personne
     * @param password Le mot de passe associé à nom d'utilisateur
     */
    public void checkAndAdd(String username, String password) {
        userThreadLocal.set(securityService.getUserWithPassword(username, password));
    }

    public SecurityLevel getCurrentUserRole() {
        User user = userThreadLocal.get();
        if (user == null) {
            return SecurityLevel.ANONYMOUS;
        } else {
            return user.getRole();
        }
    }

    public String getCurrentUsername() {
        User user = userThreadLocal.get();
        if (user == null) {
            return null;
        } else {
            return user.getUsername();
        }
    }
}

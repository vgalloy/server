package com.vgalloy.server.aspect.security;

import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 15/12/15.
 */
@Component
public class SecurityApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityApi.class);
    private ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
    /**
     * On est obligé d'utiliser le DAO. L'obtention du user via le service serait impossible puisque certains rôles
     * sont necessaires. Or à ce stade aucun user n'est present dans le context de sécurité.
     */
    @Autowired
    private UserDao userDao;

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
        User user;
        if (username == null || username.trim().isEmpty()) {
            LOGGER.info("Un utilisateur anonyme vient de ce connecter");
        } else {
            user = userDao.getById(username);
            if(user == null) {
                LOGGER.info("Aucun utilisateur avec le nom d'utilisateur '{}'", username);
            } else if ((user.getPassword() != null && user.getPassword().equals(password)) || (user.getPassword() == null && password == null)) {
                LOGGER.info("L'utilisateur '{}' vient de ce connecter", username);
                userThreadLocal.set(user);
            } else {
                LOGGER.info("L'utilisateur '{}' vient d'échouer dans sa tentative de connection", username);
            }
        }
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

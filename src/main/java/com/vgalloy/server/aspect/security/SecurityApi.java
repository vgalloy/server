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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
    /**
     * On est obliger d'utiliser le DAO. L'obtention du user via le service serait impossible puisque certain rôle
     * sont necessaires. Or à ce stade aucun user n'est present dans le context de sécurité.
     */
    @Autowired
    private UserDao userDao;

    /**
     * Cette méthode test si la combinaison nom d'utilisateur et mot de passe et correct.
     * Si cette combinaison est correcte, les droits de l'utilisateur seront disponibles partout dans le tread lié à la
     * requête.
     * Si la combinaison est incorrect (par exemple en cas d'utilisateur anonyme) aucun droit ne sera disponible
     *
     * @param username Le nom d'utilisateur de la personne
     * @param password Le mot de passe associé à nom d'utilisateur
     */
    public void checkAndAdd(String username, String password) {
        User user;
        if (username == null || username.trim().isEmpty()) {
            logger.info("Un utilisateur anonyme vient de ce connecter");
        } else {
            user = userDao.getById(username);
            if(user == null) {
                logger.info("Aucun utilisateur avec le nom d'utilisateur '{}'", username);
            } else if ((user.getPassword() != null && user.getPassword().equals(password)) || (user.getPassword() == null && password == null)) {
                logger.info("L'utilisateur '{}' vient de ce connecter", username);
                userThreadLocal.set(user);
            } else {
                logger.info("L'utilisateur '{}' vient d'échouer dans sa tentative de connection", username);
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

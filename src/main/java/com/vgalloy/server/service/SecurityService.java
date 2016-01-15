package com.vgalloy.server.service;

import com.vgalloy.server.dao.model.entity.User;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
public interface SecurityService {
    /**
     * Permet de verifier si la combinaison nom d'utilisateur/mot de passe est correct.
     *
     * @param username Le nom d'utilisateur
     * @param password Le mot de passe
     * @return true si le mot de passe correspont, faux sinon
     */
    boolean checkUsernameAndPassword(String username, String password);

    /**
     * Permet d'obtenir l'utilisateur correspondant à la combinaison nom d'utilisateur/mot de passe.
     *
     * @param username Le nom d'utilisateur
     * @param password Le mot de passe
     * @return L'utilisateur correspondant à la combinaison. Sinon null.
     */
    User getUserWithPassword(String username, String password);
}

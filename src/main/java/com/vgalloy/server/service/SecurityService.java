package com.vgalloy.server.service;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 28/01/16.
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
}

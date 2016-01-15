package com.vgalloy.server.webservice;

import com.vgalloy.server.webservice.dto.AuthenticationDTO;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
public interface LoginWebService {
    /**
     * Permet de verifier si la combinaison nom d'utilisateur/mot de passe est correct.
     *
     * @param authenticationDTO Le nom d'utilisateur et le mot de passe
     * @return true si le mot de passe correspont, faux sinon
     */
    boolean checkPassword(AuthenticationDTO authenticationDTO);
}

package com.vgalloy.server.webservice;

import com.vgalloy.server.webservice.dto.AuthenticationDto;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
public interface LoginWebService {
    /**
     * Permet de verifier si la combinaison nom d'utilisateur/mot de passe est correct.
     *
     * @param authenticationDto Le nom d'utilisateur et le mot de passe
     * @return true si le mot de passe correspont, faux sinon
     */
    boolean checkPassword(AuthenticationDto authenticationDto);

    /**
     * Generère l'url permettant d'otenir le token de Google.
     *
     * @return L'url sous forme de String
     */
    String getGoogleTokenUrl();

    /**
     * Enregistre le token de Google.
     *
     * @param token Le code
     * @return true si tout c'est bien passé, sinon false
     */
    boolean setToken(String token);
}

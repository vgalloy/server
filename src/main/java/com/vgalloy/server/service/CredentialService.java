package com.vgalloy.server.service;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 28/01/16.
 */
public interface CredentialService {
    /**
     * Enregistre le code necessaire a la creation des Credential.
     *
     * @param token Le le formis par l'api de google
     * @return true si tout c'est bien passé, sinon false
     */
    boolean setToken(String token);

    /**
     * Generère l'url permettant d'otenir le token de Google.
     *
     * @return L'url sous forme de String
     */
    String generateUrl();
}

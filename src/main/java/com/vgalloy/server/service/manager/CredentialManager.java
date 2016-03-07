package com.vgalloy.server.service.manager;

import com.google.api.client.auth.oauth2.Credential;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 26/01/16.
 */
public interface CredentialManager {

    /**
     * Retorune les Credential permetant une connexion au drive.
     *
     * @return Les Credential si ceux-ci sont disponible, null sinon
     */
    Credential getCredential();

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

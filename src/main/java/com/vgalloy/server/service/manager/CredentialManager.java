package com.vgalloy.server.service.manager;

import com.google.api.client.auth.oauth2.Credential;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 26/01/16.
 */
public interface CredentialManager {

    /**
     * Get the credential.
     *
     * @return The credential if the token is present or null
     */
    Credential getCredential();

    /**
     * Save the token.
     *
     * @param token The token to save
     */
    void setToken(String token);

    /**
     * Create url for the google token.
     *
     * @return The url as a String.
     */
    String generateGoogleTokenUrl();
}

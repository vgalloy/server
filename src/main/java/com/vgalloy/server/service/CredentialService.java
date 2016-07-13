package com.vgalloy.server.service;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 28/01/16.
 */
public interface CredentialService {

    /**
     * Save the token.
     *
     * @param token The google's token
     */
    void setToken(String token);

    /**
     * Create url for the google token.
     *
     * @return The url as a String.
     */
    String generateGoogleTokenUrl();
}

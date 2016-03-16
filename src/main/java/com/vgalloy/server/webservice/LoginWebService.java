package com.vgalloy.server.webservice;

import com.vgalloy.server.webservice.dto.AuthenticationDto;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 06/01/16.
 */
public interface LoginWebService {

    /**
     * Check if username/password is correct.
     *
     * @param authenticationDto The username and the password
     * @return true if username match with password
     */
    boolean checkPassword(AuthenticationDto authenticationDto);

    /**
     * Create url for the google token.
     *
     * @return The url as a String.
     */
    String getGoogleTokenUrl();

    /**
     * Save the token.
     *
     * @param token The google's token
     * @return true if nothing wrong happen
     */
    boolean setToken(String token);
}

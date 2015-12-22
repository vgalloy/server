package com.vgalloy.server.aspect.security;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 18/12/15.
 */
public class SecurityException extends RuntimeException {
    public static final String UNAUTHORIZED = "Vous n'êtes pas authorisé à acceder à cette ressource";
    public SecurityException(String message) {
        super(message);
    }
}

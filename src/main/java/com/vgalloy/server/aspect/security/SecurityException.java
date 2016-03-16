package com.vgalloy.server.aspect.security;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 18/12/15.
 */
public class SecurityException extends RuntimeException {

    public static final String UNAUTHORIZED = "Vous n'êtes pas autorisé à accéder à cette ressource";

    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     */
    public SecurityException(String message) {
        super(message);
    }
}

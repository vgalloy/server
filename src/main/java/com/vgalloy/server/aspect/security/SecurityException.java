package com.vgalloy.server.aspect.security;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 18/12/15.
 */
public class SecurityException extends RuntimeException {
    public static final String UNAUTHORIZED = "Vous n'êtes pas autorisé à acceder à cette ressource";

    /**
     * Constructeur avec un message d'erreur.
     *
     * @param message Le message d'erreur
     */
    public SecurityException(String message) {
        super(message);
    }
}

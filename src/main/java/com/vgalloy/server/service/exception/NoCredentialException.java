package com.vgalloy.server.service.exception;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 27/01/16.
 */
public class NoCredentialException extends Exception {
    /**
     * Constructeur avec un message d'erreur et la cause.
     *
     * @param message Le message d'erreur
     */
    public NoCredentialException(String message) {
        super(message);
    }
}

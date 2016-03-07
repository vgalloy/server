package com.vgalloy.server.service.exception;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 27/01/16.
 */
public class GoogleServiceException extends RuntimeException {

    /**
     * Constructeur avec un message d'erreur et la cause.
     *
     * @param message Le message d'erreur
     * @param cause   La cause de l'erreur
     */
    public GoogleServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

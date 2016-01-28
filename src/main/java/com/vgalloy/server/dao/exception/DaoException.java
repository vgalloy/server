package com.vgalloy.server.dao.exception;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 12/12/15.
 */
public class DaoException extends RuntimeException {
    /**
     * Constructeur avec un message d'erreur.
     *
     * @param message Le message d'erreur
     */
    public DaoException(String message) {
        super(message);
    }
}

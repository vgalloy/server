package com.vgalloy.server.dao.exception;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 12/12/15.
 */
public class DaoException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     */
    public DaoException(String message) {
        super(message);
    }
}

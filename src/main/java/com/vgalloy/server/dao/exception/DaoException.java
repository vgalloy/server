package com.vgalloy.server.dao.exception;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 12/12/15.
 */
public class DaoException extends RuntimeException {

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }
}

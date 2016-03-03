package com.vgalloy.server.service.exception;

import com.vgalloy.server.service.error.Errors;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 10/12/15.
 */
public class ServiceException extends RuntimeException {

    private final Errors errors;

    /**
     * Constructeur avec une liste d'erreurs.
     *
     * @param errors Les erreurs à l'origine de l'exception
     */
    public ServiceException(Errors errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return errors.toString();
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "errors=" + errors +
                '}';
    }
}

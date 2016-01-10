package com.vgalloy.server.service.exception;

import com.vgalloy.server.error.Errors;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 10/12/15.
 */
public class ServiceException extends RuntimeException {
    private final Errors errors;

    public ServiceException(Errors errors) {
        super();
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

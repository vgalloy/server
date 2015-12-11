package com.vgalloy.server.service.exception;

import com.vgalloy.server.error.Errors;

/**
 * Created by Vincent Galloy on 10/12/15.
 */
public class ServiceException extends RuntimeException {
    private Errors errors;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Errors errors) {
        super();
        this.errors = errors;
    }
}

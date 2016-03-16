package com.vgalloy.server.service.error;

import java.io.Serializable;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 11/12/15.
 */
public class Error implements Serializable {

    private final String message;

    /**
     * Constructor with error message.
     *
     * @param message The error message.
     */
    public Error(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "message='" + message + '\'' +
                '}';
    }
}

package com.vgalloy.server.error;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 11/12/15.
 */
public class Error {
    private final String cause;

    public Error(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "Error{" +
                "cause='" + cause + '\'' +
                '}';
    }
}

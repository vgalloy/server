package com.vgalloy.server.error;

import java.io.Serializable;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 11/12/15.
 */
public class Error implements Serializable {
    private final String message;

    /**
     * Constructeur avec un message d'erreur.
     *
     * @param message Le message d'erreur
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

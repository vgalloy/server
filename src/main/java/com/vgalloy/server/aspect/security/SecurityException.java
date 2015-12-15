package com.vgalloy.server.aspect.security;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 18/12/15.
 */
public class SecurityException extends RuntimeException {
    public SecurityException(String message) {
        super(message);
    }
}

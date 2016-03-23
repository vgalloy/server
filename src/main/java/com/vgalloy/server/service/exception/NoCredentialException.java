package com.vgalloy.server.service.exception;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 27/01/16.
 */
public class NoCredentialException extends Exception {

    private static final String NO_CREDENTIAL_DEFINE = "No credential define. You should define google credential";

    /**
     * Constructs a new runtime exception with the specified detail message.
     */
    public NoCredentialException() {
        super(NO_CREDENTIAL_DEFINE);
    }
}

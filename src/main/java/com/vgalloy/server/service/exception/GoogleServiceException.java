package com.vgalloy.server.service.exception;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 27/01/16.
 */
public class GoogleServiceException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public GoogleServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

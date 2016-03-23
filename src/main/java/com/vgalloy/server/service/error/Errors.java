package com.vgalloy.server.service.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 11/12/15.
 */
public class Errors implements Serializable {

    private List<Error> errorList;

    /**
     * Constructor.
     */
    public Errors() {
        errorList = new ArrayList<>();
    }

    /**
     * Add error with functional approach.
     *
     * @param error The error
     * @return Errors with the new Error
     */
    public Errors addError(Error error) {
        errorList.add(error);
        return this;
    }

    public List<Error> getErrorList() {
        return errorList;
    }

    /**
     * Assert there is no error.
     *
     * @return true if errorList contains error.
     */
    public boolean hasError() {
        return !errorList.isEmpty();
    }

    @Override
    public String toString() {
        return "Errors{" +
                "errorList=" + errorList +
                '}';
    }
}

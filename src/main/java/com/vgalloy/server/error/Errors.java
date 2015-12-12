package com.vgalloy.server.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 11/12/15.
 */
public class Errors {
    private transient List<Error> errorList;

    public Errors() {
        this.errorList = new ArrayList<>();
    }

    public Errors addError(Error error) {
        errorList.add(error);
        return this;
    }

    public List<Error> getErrorList() {
        return errorList;
    }

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

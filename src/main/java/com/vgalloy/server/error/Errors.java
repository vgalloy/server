package com.vgalloy.server.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 11/12/15.
 */
public class Errors {
    private List<Error> errors;

    public Errors() {
        this.errors = new ArrayList<>();
    }

    public Errors addError(Error error) {
        errors.add(error);
        return this;
    }

    public boolean hasError() {
        return !errors.isEmpty();
    }

    @Override
    public String toString() {
        return "Errors{" +
                "errors=" + errors +
                '}';
    }
}

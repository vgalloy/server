package com.vgalloy.server.error;

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
     * Constructeur.
     */
    public Errors() {
        this.errorList = new ArrayList<>();
    }

    /**
     * Permet d'ajouter des erreurs de manière fonctionnel.
     *
     * @param error L'erreur à ajouter
     * @return L'Errors modifié.
     */
    public Errors addError(Error error) {
        errorList.add(error);
        return this;
    }

    public List<Error> getErrorList() {
        return errorList;
    }

    /**
     * Permet de s'assurer qu'aucune erreur n'a été trouvées.
     *
     * @return true si l'Errors contient des erreurs.
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

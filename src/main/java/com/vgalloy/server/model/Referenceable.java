package com.vgalloy.server.model;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
public interface Referenceable {
    /**
     * Tout objet referençable à un id.
     *
     * @return L'id de l'objet referençable
     */
    String getId();

    /**
     * Tout objet referençable à un id.
     *
     * @param id Defini l'id de l'objet
     */
    void setId(String id);

}

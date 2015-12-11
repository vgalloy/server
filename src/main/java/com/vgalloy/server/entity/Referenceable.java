package com.vgalloy.server.entity;

import org.mongojack.Id;
import org.mongojack.ObjectId;

/**
 * Created by Vincent Galloy on 09/12/15.
 */
public abstract class Referenceable {

    @Id
    @ObjectId
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

package com.vgalloy.server.entity;

import org.mongojack.Id;
import org.mongojack.ObjectId;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
public interface Referenceable {
    String getId();

    void setId(String id);

}

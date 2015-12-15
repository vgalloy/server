package com.vgalloy.server.dao.model;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 12/12/15.
 */
public interface Versionable {

    Long getVersion();

    void setVersion(Long version);

}

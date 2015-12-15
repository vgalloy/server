package com.vgalloy.server.dao.model.entity;

import com.vgalloy.server.dao.model.Versionable;

import java.util.Objects;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 12/12/15.
 */
public class Event implements Versionable {
    private Long version;
    private String desciption;

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String event) {
        this.desciption = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event1 = (Event) o;
        return Objects.equals(version, event1.version) &&
                Objects.equals(desciption, event1.desciption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, desciption);
    }

    @Override
    public String toString() {
        return "Event{" +
                ", version=" + version +
                ", event='" + desciption + '\'' +
                '}';
    }
}

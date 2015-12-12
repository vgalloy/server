package com.vgalloy.server.entity;

import org.mongojack.Id;
import org.mongojack.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 12/12/15.
 */
public class Calendar implements Versionable, Referenceable {

    @Id
    @ObjectId
    private String id;
    private Long version;

    private List<Event> eventList = new ArrayList<>(30);

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return Objects.equals(id, calendar.id) &&
                Objects.equals(version, calendar.version) &&
                Objects.equals(eventList, calendar.eventList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, eventList);
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", eventList=" + eventList +
                '}';
    }
}

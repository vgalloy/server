package com.vgalloy.server.entity;

import org.mongojack.Id;
import org.mongojack.ObjectId;

import java.util.Objects;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 12/12/15.
 */
public class Event implements Versionable, Referenceable {
    @Id
    @ObjectId
    private String id;
    private Long version;
    private String event;

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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event1 = (Event) o;
        return Objects.equals(id, event1.id) &&
                Objects.equals(version, event1.version) &&
                Objects.equals(event, event1.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, event);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", event='" + event + '\'' +
                '}';
    }
}

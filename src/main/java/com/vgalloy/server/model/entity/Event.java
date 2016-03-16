package com.vgalloy.server.model.entity;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public class Event {

    private String action;

    /**
     * Constructor.
     */
    public Event() {
        super();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Event{" +
                "action='" + action + '\'' +
                '}';
    }
}

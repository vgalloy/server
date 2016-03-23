package com.vgalloy.server.model.entity;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public class Day {

    private final Event event;

    /**
     * Constructor.
     */
    public Day() {
        event = new Event();
    }

    /**
     * Constructor with event.
     *
     * @param event L'event
     */
    public Day(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return "Day{" +
                "event=" + event +
                '}';
    }
}

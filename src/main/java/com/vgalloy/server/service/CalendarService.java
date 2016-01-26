package com.vgalloy.server.service;


import com.vgalloy.server.model.entity.Calendar;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public interface CalendarService {
    /**
     * Permet l'obtention du calendrier.
     *
     * @return Le calendrier
     */
    Calendar getCalendar();
}

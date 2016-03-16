package com.vgalloy.server.webservice;

import com.vgalloy.server.model.entity.Calendar;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public interface CalendarWebService {

    /**
     * Get the calendar.
     *
     * @return le calendrier
     */
    Calendar getCalendar();
}

package com.vgalloy.server.service.manager;

import com.vgalloy.server.model.entity.Calendar;
import com.vgalloy.server.service.exception.NoCredentialException;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public interface GoogleManager {

    /**
     * Get the calendar.
     *
     * @return The calendar
     * @throws NoCredentialException If no credential are define yet.
     */
    Calendar getCalendar() throws NoCredentialException;
}

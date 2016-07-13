package com.vgalloy.server.service.impl;
import org.junit.Test;

import com.vgalloy.server.model.entity.Calendar;
import com.vgalloy.server.service.CalendarService;

import static org.junit.Assert.assertNull;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 03/05/16.
 */
public class CalendarServiceImplTest {

    @Test
    public void testEmptyCalendar() {
        // GIVEN
        CalendarService calendarService = new CalendarServiceImpl();

        // WHEN
        Calendar calendar = calendarService.getCalendar();

        // THEN
        assertNull(calendar);
    }
}
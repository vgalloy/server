package com.vgalloy.server.webservice.impl;

import com.vgalloy.server.model.entity.Calendar;
import com.vgalloy.server.service.CalendarService;
import com.vgalloy.server.webservice.CalendarWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
@RestController
@RequestMapping("calendar")
public class CalendarWebServiceImpl implements CalendarWebService {
    @Autowired
    private CalendarService calendarService;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public Calendar getCalendar() {
        return calendarService.getCalendar();
    }
}

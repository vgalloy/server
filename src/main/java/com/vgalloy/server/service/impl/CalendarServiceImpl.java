package com.vgalloy.server.service.impl;

import com.vgalloy.server.aspect.security.Security;
import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.model.entity.Calendar;
import com.vgalloy.server.service.CalendarService;
import com.vgalloy.server.service.exception.NoCredentialException;
import com.vgalloy.server.service.manager.GoogleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 26/01/16.
 */
@Service
public class CalendarServiceImpl implements CalendarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalendarServiceImpl.class);
    @Autowired
    private GoogleManager googleManager;
    private Calendar calendar;

    @Override
    @Security({SecurityLevel.ADMIN, SecurityLevel.USER})
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * Permet d'actualiser regulièrement le calendrier.
     */
    @Scheduled(fixedRate = 10_000)
    protected void refreshCalendar() {
        try {
            calendar = googleManager.getCalendar();
        } catch (NoCredentialException e) {
            LOGGER.warn("refreshCalendar fails");
        }
    }
}

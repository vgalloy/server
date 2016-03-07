package com.vgalloy.server.service.manager;

import com.vgalloy.server.model.entity.Calendar;
import com.vgalloy.server.service.exception.NoCredentialException;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 25/01/16.
 */
public interface GoogleManager {

    /**
     * Permet l'obtention du calendrier.
     *
     * @return Le calendrier
     * @throws NoCredentialException Retourne une exception si aucun credential n'est encore defini
     */
    Calendar getCalendar() throws NoCredentialException;
}

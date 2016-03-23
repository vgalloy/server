package com.vgalloy.server.service.manager;

import java.util.List;

import com.vgalloy.server.model.entity.Description;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 08/03/16.
 */
public interface DescriptionManager {

    /**
     * Get all the description from Google services.
     *
     * @return The list with all the descriptions
     */
    List<Description> getAllFromWeb();

    /**
     * Save the given description.
     *
     * @param description The description to save
     * @return The description
     */
    Description save(Description description);
}

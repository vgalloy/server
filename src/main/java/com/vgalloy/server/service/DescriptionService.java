package com.vgalloy.server.service;

import java.util.List;

import com.vgalloy.server.model.entity.Description;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 07/03/16.
 */
public interface DescriptionService {

    /**
     * Get all the description.
     *
     * @return The list with all the descriptions
     */
    List<Description> getAll();

    /**
     * Return the character description.
     *
     * @param name The name of the character
     * @return The description of the character
     */
    Description getDescriptionByName(String name);

    /**
     * Refresh all descriptions.
     */
    void refresh();
}

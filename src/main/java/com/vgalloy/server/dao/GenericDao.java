package com.vgalloy.server.dao;

import com.vgalloy.server.model.Referable;

import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 02/12/15.
 */
public interface GenericDao<T extends Referable> {

    /**
     * Retrieve all elements from a collection.
     *
     * @return List with all elements
     */
    List<T> getAll();

    /**
     * Insert a new element.
     *
     * @param t The new element to insert
     * @return The new element
     */
    T create(T t);

    /**
     * Find the element with the given id. Return null if no element found.
     *
     * @param id The id
     * @return L'object with the given id
     */
    T getById(String id);

    /**
     * Update an existing element.
     *
     * @param t The element to update
     * @return The modify object
     */
    T update(T t);

    /**
     * Delete the element with the given id.
     *
     * @param id The id
     */
    void deleteById(String id);

    /**
     * Remove all element of the collection.
     */
    void removeAll();
}

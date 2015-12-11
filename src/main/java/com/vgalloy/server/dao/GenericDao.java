package com.vgalloy.server.dao;

import com.vgalloy.server.entity.Referenceable;

import java.util.List;

/**
 * Created by Vincent Galloy on 02/12/15.
 */
public interface GenericDao<T extends Referenceable> {

    List<T> getAll();

    void create(T t);

    T getById(String id);

    void update(T t);

    void delete(String id);

}

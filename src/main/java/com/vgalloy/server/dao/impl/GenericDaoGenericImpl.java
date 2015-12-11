package com.vgalloy.server.dao.impl;

import com.vgalloy.server.dao.GenericDao;
import com.vgalloy.server.entity.Referenceable;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import java.util.List;

/**
 * Created by Vincent Galloy on 09/12/15.
 */
public abstract class GenericDaoGenericImpl<T extends Referenceable> implements GenericDao<T> {

    protected JacksonDBCollection<T, String> collection;

    @Override
    public List<T> getAll() {
        return collection.find().toArray();
    }

    @Override
    public void create(T t) {
        WriteResult writeResult = collection.insert(t);
        t.setId(writeResult.getSavedId().toString());
    }

    @Override
    public T getById(String id) {
        return collection.findOneById(id);
    }

    @Override
    public void update(T o) {
        collection.save(o);
    }

    @Override
    public void delete(String id) {
        collection.removeById(id);
    }

}

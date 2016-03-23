package com.vgalloy.server.dao.impl;

import java.util.List;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import com.mongodb.BasicDBObject;

import com.vgalloy.server.dao.GenericDao;
import com.vgalloy.server.model.Referable;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
public class GenericDaoImpl<T extends Referable> implements GenericDao<T> {

    private final JacksonDBCollection<T, String> collection;

    /**
     * Constructor with the collectionFactory.
     *
     * @param collection The Jackson collection
     */
    public GenericDaoImpl(JacksonDBCollection<T, String> collection) {
        this.collection = collection;
    }

    @Override
    public List<T> getAll() {
        return collection.find().toArray();
    }

    @Override
    public T create(T t) {
        WriteResult writeResult = collection.insert(t);
        t.setId(writeResult.getSavedId().toString());
        return t;
    }

    @Override
    public T getById(String id) {
        return collection.findOneById(id);
    }

    @Override
    public T update(T t) {
        collection.save(t);
        return t;
    }

    @Override
    public void deleteById(String id) {
        collection.removeById(id);
    }

    @Override
    public void removeAll() {
        collection.getDbCollection().remove(new BasicDBObject());
    }
}

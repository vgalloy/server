package com.vgalloy.server.dao.impl;

import com.mongodb.BasicDBObject;
import com.vgalloy.server.dao.GenericDao;
import com.vgalloy.server.dao.model.Referenceable;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
public class GenericDaoImpl<T extends Referenceable> implements GenericDao<T> {
    private JacksonDBCollection<T, String> collection;

    /**
     * Constructeur avec la collection déja créée.
     *
     * @param collection La collection Jackson
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

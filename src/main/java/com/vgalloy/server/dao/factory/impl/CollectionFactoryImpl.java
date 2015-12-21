package com.vgalloy.server.dao.factory.impl;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.vgalloy.server.dao.exception.DaoException;
import com.vgalloy.server.dao.factory.CollectionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 *         This class keep the different databases into a map. This permit to not recreate a database for each request.
 */
@Component
public class CollectionFactoryImpl implements CollectionFactory {
    private static final String DATABASE_NAME = "Test";
    private DB database;
    private Map<String, DBCollection> collections;

    /**
     * Constructor.
     */
    public CollectionFactoryImpl() {
        try {
            MongoClient mongoClient = new MongoClient();
            database = mongoClient.getDB(DATABASE_NAME);
        } catch (Exception e) {
            throw new DaoException("Can not create MongoClient", e);
        }
        collections = new HashMap<>();
    }


    @Override
    public DBCollection getCollection(String collectionName) {
        if (collections.containsKey(collectionName)) {
            return collections.get(collectionName);
        } else {
            DBCollection collection = database.getCollection(collectionName);
            collections.put(collectionName, collection);
            return collection;
        }
    }
}

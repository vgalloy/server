package com.vgalloy.server.dao.factory.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import com.vgalloy.server.dao.factory.CollectionFactory;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 *         This class keep the different databases into a map. This permit to not recreate a database for each request.
 */
@Component
public class CollectionFactoryImpl implements CollectionFactory {

    private final DB database;
    private final Map<String, DBCollection> collections;

    /**
     * Constructor.
     *
     * @param databaseName The database name
     * @param databaseUrl  The database url
     */
    @Autowired
    public CollectionFactoryImpl(@Value("${database.name}") String databaseName, @Value("${database.url}") String databaseUrl) {
        collections = new HashMap<>();
        MongoClient mongoClient = new MongoClient(databaseUrl);
        database = mongoClient.getDB(databaseName);
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

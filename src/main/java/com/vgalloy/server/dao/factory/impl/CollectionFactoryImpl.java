package com.vgalloy.server.dao.factory.impl;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.vgalloy.server.dao.exception.DaoException;
import com.vgalloy.server.dao.factory.CollectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 *         This class keep the different databases into a map. This permit to not recreate a database for each request.
 */
@Component
public class CollectionFactoryImpl implements CollectionFactory {
    private DB database;
    private Map<String, DBCollection> collections;

    /**
     * Constructor.
     */
    public CollectionFactoryImpl() {
        Properties prop = new Properties();
        InputStream intputStream = null;
        try {
            intputStream = CollectionFactoryImpl.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(intputStream);
            MongoClient mongoClient = new MongoClient();
            database = mongoClient.getDB(prop.getProperty("database.name"));
        } catch (IOException e) {
            throw new DaoException("Can not create database", e);
        } finally {
            if (intputStream != null) {
                try {
                    intputStream.close();
                } catch (IOException e) {
                    throw new DaoException("Can not close properties output stream", e);
                }
            }
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

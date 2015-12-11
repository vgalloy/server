package com.vgalloy.server.factory;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vincent Galloy on 09/12/15.
 * This class keep the different databases into a map. This permit to not recreate a database for each request.
 */

public enum DBFactory {
    INSTANCE;

    private MongoClient mongoClient;
    private Map<String, DB> databases;

    /**
     * Constructor
     */
    DBFactory() {
        try {
            mongoClient = new MongoClient();
        } catch (Exception e) {
            throw new RuntimeException("Can not create MongoClient", e);
        }
        databases = new HashMap<>();
    }

    /**
     * Obtains a dataBase.
     *
     * @param databaseName The name of the database.
     * @return A mongoDB database;
     */
    public static DB getDatabase(String databaseName) {
        return INSTANCE.getDB(databaseName);
    }

    /**
     * Obtains a dataBase.
     *
     * @param databaseName The name of the database.
     * @return A mongoDB database;
     */
    private DB getDB(String databaseName) {
        if (databases.containsKey(databaseName)) {
            return databases.get(databaseName);
        } else {
            DB db = mongoClient.getDB(databaseName);
            databases.put(databaseName, db);
            return db;
        }
    }
}

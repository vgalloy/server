package com.vgalloy.server.dao.factory;

import com.mongodb.DBCollection;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 *         This class keep the different collection into a map. This permit to not recreate a database for each request.
 */
public interface CollectionFactory {
    /**
     * Obtains a collection
     *
     * @param collectionName The name of the collection
     * @return A mongoDB collection
     */
    DBCollection getCollection(String collectionName);
}

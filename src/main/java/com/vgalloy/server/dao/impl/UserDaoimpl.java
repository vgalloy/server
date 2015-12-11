package com.vgalloy.server.dao.impl;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.vgalloy.server.entity.User;
import com.vgalloy.server.factory.DBFactory;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

/**
 * Created by Vincent Galloy on 09/12/15.
 */
@Repository
public class UserDaoimpl extends GenericDaoGenericImpl <User> {
    public static final String DATABASE = "Example";
    public static final String COLLECTION = "person";

    public UserDaoimpl() {
        super();
        DB database = DBFactory.getDatabase(DATABASE);
        DBCollection dbCollection = database.getCollection(COLLECTION);
        this.collection = JacksonDBCollection.wrap(dbCollection, User.class, String.class);
    }
}

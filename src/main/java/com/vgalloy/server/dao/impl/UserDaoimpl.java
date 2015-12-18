package com.vgalloy.server.dao.impl;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.factory.DBFactory;
import com.vgalloy.server.dao.model.entity.User;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.springframework.stereotype.Repository;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
@Repository
public class UserDaoimpl extends GenericDaoImpl<User> implements UserDao {
    private static final String DATABASE = "Example";
    private static final String COLLECTION = "person";

    /**
     * Constructor
     */
    public UserDaoimpl() {
        super();
        DB database = DBFactory.getDatabase(DATABASE);
        DBCollection dbCollection = database.getCollection(COLLECTION);
        this.collection = JacksonDBCollection.wrap(dbCollection, User.class, String.class);
    }

    @Override
    public User getByUsername(String username) {
        DBCursor<User> cursor = this.collection.find(DBQuery.is("username", username));
        if(cursor.hasNext()) {
            return cursor.next();
        }
        return null;
    }

    @Override
    public void deleteByUsername(String username) {
        this.collection.remove(DBQuery.is("username", username));
    }

}

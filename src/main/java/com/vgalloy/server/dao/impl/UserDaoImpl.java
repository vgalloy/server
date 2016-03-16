package com.vgalloy.server.dao.impl;

import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.dao.exception.DaoException;
import com.vgalloy.server.dao.factory.CollectionFactory;
import com.vgalloy.server.model.entity.User;
import com.vgalloy.server.dao.validator.UserDaoValidator;
import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {

    private static final String COLLECTION = "user";

    @Autowired
    private UserDaoValidator userDaoValidator;

    /**
     * Constructor with the collectionFactory.
     *
     * @param collectionFactory The Jackson collection
     */
    @Autowired
    public UserDaoImpl(CollectionFactory collectionFactory) {
        super(JacksonDBCollection.wrap(collectionFactory.getCollection(COLLECTION), User.class, String.class));
    }

    @Override
    public User create(User user) {
        if (!userDaoValidator.isUserOkForCreateOrUpdate(user)) {
            throw new DaoException("Invalid user");
        }
        return super.create(user);
    }

    @Override
    public User update(User user) {
        if (!userDaoValidator.isUserOkForCreateOrUpdate(user)) {
            throw new DaoException("Invalid user");
        }
        return super.update(user);
    }
}

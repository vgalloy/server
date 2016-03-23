package com.vgalloy.server.dao.impl;

import org.mongojack.JacksonDBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vgalloy.server.dao.DescriptionDao;
import com.vgalloy.server.dao.exception.DaoException;
import com.vgalloy.server.dao.factory.CollectionFactory;
import com.vgalloy.server.dao.validator.DescriptionDaoValidator;
import com.vgalloy.server.model.entity.Description;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 07/03/16.
 */
@Repository
public class DescriptionDaoImpl extends GenericDaoImpl<Description> implements DescriptionDao {

    private static final String COLLECTION = "description";

    @Autowired
    private DescriptionDaoValidator descriptionDaoValidator;

    /**
     * Constructor with the collectionFactory.
     *
     * @param collectionFactory The Jackson collection
     */
    @Autowired
    public DescriptionDaoImpl(CollectionFactory collectionFactory) {
        super(JacksonDBCollection.wrap(collectionFactory.getCollection(COLLECTION), Description.class, String.class));
    }

    @Override
    public Description create(Description description) {
        if (!descriptionDaoValidator.isDescriptionOkForCreateOrUpdate(description)) {
            throw new DaoException("Invalid description");
        }
        return super.create(description);
    }

    @Override
    public Description update(Description description) {
        if (!descriptionDaoValidator.isDescriptionOkForCreateOrUpdate(description)) {
            throw new DaoException("Invalid description");
        }
        return super.update(description);
    }
}

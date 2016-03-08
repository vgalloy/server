package com.vgalloy.server.service.manager.impl;

import com.vgalloy.server.dao.DescriptionDao;
import com.vgalloy.server.model.entity.Description;
import com.vgalloy.server.service.manager.DescriptionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 08/03/16.
 */
@Service
public class DescriptionManagerImpl implements DescriptionManager {

    @Autowired
    private DescriptionDao descriptionDao;

    @Override
    public List<Description> getAllFromWeb() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Description save(Description description) {
        return descriptionDao.create(description);
    }
}

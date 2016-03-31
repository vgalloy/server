package com.vgalloy.server.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vgalloy.server.dao.DescriptionDao;
import com.vgalloy.server.model.entity.Description;
import com.vgalloy.server.service.DescriptionService;
import com.vgalloy.server.service.manager.DescriptionManager;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 07/03/16.
 */
@Service
public class DescriptionServiceImpl implements DescriptionService {

    @Autowired
    private DescriptionDao descriptionDao;
    @Autowired
    private DescriptionManager descriptionManager;

    @Override
    public List<Description> getAll() {
        return descriptionDao.getAll();
    }

    @Override
    public Description getDescriptionByName(String name) {
        return descriptionDao.getById(name);
    }

    @Override
    public void refresh() {
        List<Description> descriptions = descriptionManager.getAllFromWeb();
        descriptions.stream().forEach(descriptionManager::save);
    }
}

package com.vgalloy.server.service.manager.impl;

import java.util.List;
import org.springframework.stereotype.Service;

import com.vgalloy.server.model.entity.Description;
import com.vgalloy.server.service.manager.DescriptionManager;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 08/03/16.
 */
@Service
public class DescriptionManagerImpl implements DescriptionManager {

    @Override
    public List<Description> getAllFromWeb() {
        throw new UnsupportedOperationException();
    }
}

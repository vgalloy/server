package com.vgalloy.server.service.manager;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vgalloy.server.configuration.CommonConfiguration;
import com.vgalloy.server.model.entity.Description;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 08/03/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CommonConfiguration.class)
public interface DescriptionManager {

    /**
     * Get all the description from Google services.
     *
     * @return The list with all the descriptions
     */
    List<Description> getAllFromWeb();
}

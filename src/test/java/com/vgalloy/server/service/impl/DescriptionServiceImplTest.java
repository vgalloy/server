package com.vgalloy.server.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vgalloy.server.configuration.CommonConfiguration;
import com.vgalloy.server.dao.DescriptionDao;
import com.vgalloy.server.model.entity.Description;
import com.vgalloy.server.service.DescriptionService;
import com.vgalloy.server.service.manager.DescriptionManager;

import static org.junit.Assert.assertEquals;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 03/05/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CommonConfiguration.class)
public class DescriptionServiceImplTest {

    private final Description description1 = new Description("name1", "description");
    private final Description description2 = new Description("name2", "description2");
    private List<Description> descriptions;
    @InjectMocks
    private DescriptionServiceImpl descriptionService;
    @Mock
    private DescriptionDao descriptionDao;
    @Mock
    private DescriptionManager descriptionManager;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        descriptions = new ArrayList<>();
        descriptions.add(description1);
        descriptions.add(description2);
        Mockito.when(descriptionDao.getAll()).thenReturn(descriptions);
    }

    @Test
    public void simpleTest() {
        // WHEN
        List<Description> result = descriptionService.getAll();

        // THEN
        assertEquals(descriptions, result);
    }
}
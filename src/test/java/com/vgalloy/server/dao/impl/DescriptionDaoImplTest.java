package com.vgalloy.server.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vgalloy.server.StartServer;
import com.vgalloy.server.dao.DescriptionDao;
import com.vgalloy.server.dao.exception.DaoException;
import com.vgalloy.server.model.entity.Description;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 21/12/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StartServer.class)
public class DescriptionDaoImplTest {

    @Autowired
    private DescriptionDao descriptionDao;

    @Before
    public void init() {
        descriptionDao.removeAll();
    }

    @Test
    public void testCreateOk() {
        Description description = new Description("name", "desc");
        descriptionDao.create(description);
        assertEquals(descriptionDao.getById(description.getId()), new Description("name", "desc"));
    }

    @Test(expected = DaoException.class)
    public void testCreateWithNullName() {
        descriptionDao.create(new Description(null, "desc"));
    }

    @Test(expected = DaoException.class)
    public void testCreateWithEmptyName() {
        descriptionDao.create(new Description(" ", "test"));
    }

    @Test
    public void testUpdateOk() {
        Description description = new Description("name", "desc");
        descriptionDao.create(description);
        description.setText("new desc");
        descriptionDao.update(description);
        assertEquals(description, descriptionDao.getById(description.getId()));
    }

    @Test(expected = DaoException.class)
    public void testUpdateWithNullName() {
        descriptionDao.update(new Description(null, "desc"));
    }

    @Test(expected = DaoException.class)
    public void testUpdateWithEmptyName() {
        descriptionDao.update(new Description("   ", "desc"));
    }

    @Test
    public void testGetOk() {
        Description description = new Description("name", "desc");
        descriptionDao.create(description);
        assertEquals(description, descriptionDao.getById(description.getId()));
    }

    @Test
    public void testGetNullId() {
        assertNull(descriptionDao.getById(null));
    }

    @Test
    public void testGetEmptyId() {
        assertNull(descriptionDao.getById(" "));
    }

    @Test
    public void testDeleteWithNullId() {
        try {
            descriptionDao.deleteById(null);
        } catch (Exception e) {
            fail("Exception when try to delete with null id");
        }
    }

    @Test
    public void testDeleteWithNoExistingId() {
        try {
            descriptionDao.deleteById("test");
        } catch (Exception e) {
            fail("Exception when try to delete with not existing id");
        }
    }

    @Test
    public void testDeleteWithCorrectId() {
        Description description = new Description("name", "desc");
        descriptionDao.create(description);
        descriptionDao.deleteById(description.getId());
        assertEquals(0, descriptionDao.getAll().size());
    }
}
package com.vgalloy.server.webservice;

import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.model.entity.User;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 28/01/16.
 */
public abstract class AbstractWebServiceImplTest {
    protected static final String ADMIN = "ADMIN";
    protected static final String USER = "USER";

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() {
        userDao.removeAll();
        User admin = new User(ADMIN, ADMIN, SecurityLevel.ADMIN);
        userDao.create(admin);
        User user = new User(USER, USER, SecurityLevel.USER);
        userDao.create(user);
    }
}

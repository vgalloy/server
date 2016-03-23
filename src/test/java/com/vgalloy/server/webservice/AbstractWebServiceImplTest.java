package com.vgalloy.server.webservice;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jayway.restassured.RestAssured;

import com.vgalloy.server.StartServer;
import com.vgalloy.server.aspect.security.SecurityLevel;
import com.vgalloy.server.dao.UserDao;
import com.vgalloy.server.model.entity.User;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 28/01/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = StartServer.class)
@WebIntegrationTest({"server.port=" + AbstractWebServiceImplTest.PORT})
public abstract class AbstractWebServiceImplTest {

    protected static final String ADMIN = "ADMIN";
    protected static final String USER = "USER";

    protected static final int PORT = 8085;

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() {
        RestAssured.port = PORT;
        userDao.removeAll();
        User admin = new User(ADMIN, ADMIN, SecurityLevel.ADMIN);
        userDao.create(admin);
        User user = new User(USER, USER, SecurityLevel.USER);
        userDao.create(user);
    }
}

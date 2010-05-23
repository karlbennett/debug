package org.youthnet.debug.dao.admin;

import static junit.framework.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.youthnet.debug.dao.util.JdbcTestUtil;
import org.youthnet.debug.domain.admin.Role;
import org.youthnet.debug.domain.admin.User;
import org.youthnet.debug.domain.common.impl.UuidTypeImpl;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * User: karl
 * Date: 19-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-config.xml",
        "/config/spring/admin-hibernate-config.xml",
        "/config/spring/admin-jdbc-config.xml"})
public class UserDaoTest {

    private static final Log log = LogFactory.getLog(UserDaoTest.class);

    @Resource(name = "jdbcTestUtil")
    JdbcTestUtil jdbcTestUtil;

    @Resource(name = "userDao")
    private UserDao userDao;

    @Before
    public void buildUp() {
        jdbcTestUtil.createCollective();
        jdbcTestUtil.createVuo();
        jdbcTestUtil.createUser();
        jdbcTestUtil.createRole();
        jdbcTestUtil.addRole();
    }

    @After
    public void tearDown() {
        jdbcTestUtil.removeRole();
        jdbcTestUtil.deleteRole();
        jdbcTestUtil.deleteUser();
        jdbcTestUtil.deleteVuo();
        jdbcTestUtil.deleteCollective();
    }

    @Test
    public void testRequest() {
        User user = userDao.request(UuidTypeImpl.fromString(jdbcTestUtil.getUSERID()));
        assertNotNull("request user", user);

        Set<Role> roles = user.getRoles();
        assertNotNull("request roles", roles);
    }

    @Test
    public void testRequestAll() {
        List<User> users = userDao.requestAll();
        assertNotNull("request users", users);
        assertTrue("users found", users.size() > 0);
    }
}

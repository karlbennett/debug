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
import org.youthnet.debug.dao.util.AdminJdbcTestUtil;
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
@ContextConfiguration(locations = {"/admin-test-config.xml",
        "/config/spring/admin-data-source-config.xml",
        "/config/spring/admin-hibernate-config.xml",
        "/config/spring/admin-jdbc-config.xml"})
public class UserDaoTest {

    private static final Log log = LogFactory.getLog(UserDaoTest.class);

    @Resource(name = "adminJdbcTestUtil")
    private AdminJdbcTestUtil adminJdbcTestUtil;

    @Resource(name = "userDao")
    private UserDao userDao;

    @Before
    public void buildUp() {
        adminJdbcTestUtil.createCollective();
        adminJdbcTestUtil.createVuo();
        adminJdbcTestUtil.createUser();
        adminJdbcTestUtil.createRole();
        adminJdbcTestUtil.addRole();
    }

    @After
    public void tearDown() {
        adminJdbcTestUtil.removeRole();
        adminJdbcTestUtil.deleteRole();
        adminJdbcTestUtil.deleteUser();
        adminJdbcTestUtil.deleteVuo();
        adminJdbcTestUtil.deleteCollective();
    }

    @Test
    public void testRequest() {
        User user = userDao.request(UuidTypeImpl.fromString(adminJdbcTestUtil.getUSERID()));
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

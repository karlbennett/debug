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
import org.youthnet.debug.dao.jdbc.JdbcDao;
import org.youthnet.debug.dao.util.JdbcTestUtil;
import org.youthnet.debug.domain.admin.Role;
import org.youthnet.debug.domain.admin.User;
import org.youthnet.debug.domain.common.impl.UuidTypeImpl;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * User: karl
 * Date: 19-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config/spring/admin-hibernate-config.xml",
        "/config/spring/admin-jdbc-config.xml"})
public class UserDaoTest {

    private static final Log log = LogFactory.getLog(UserDaoTest.class);

    @Resource(name = "adminJdbcDaoImpl")
    private JdbcDao adminJdbcDao;

    @Resource(name = "userDao")
    private UserDao userDao;

    @Before
    public void buildUp() {
        JdbcTestUtil.createCollective(adminJdbcDao);
        JdbcTestUtil.createVuo(adminJdbcDao);
        JdbcTestUtil.createUser(adminJdbcDao);
        JdbcTestUtil.createRole(adminJdbcDao);
        JdbcTestUtil.addRole(adminJdbcDao);    
    }

    @After
    public void tearDown() {
        JdbcTestUtil.removeRole(adminJdbcDao);
        JdbcTestUtil.deleteRole(adminJdbcDao);
        JdbcTestUtil.deleteUser(adminJdbcDao);
        JdbcTestUtil.deleteVuo(adminJdbcDao);
        JdbcTestUtil.deleteCollective(adminJdbcDao);
    }

    @Test
    public void testRequest() {
        User user = userDao.request(UuidTypeImpl.fromString(JdbcTestUtil.USERID));
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

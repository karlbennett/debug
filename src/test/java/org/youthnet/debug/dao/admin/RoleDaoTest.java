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

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * User: karl
 * Date: 19-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config/spring/admin-hibernate-config.xml",
        "/config/spring/admin-jdbc-config.xml"})
public class RoleDaoTest {

    private static final Log log = LogFactory.getLog(RoleDaoTest.class);

    @Resource(name = "adminJdbcDaoImpl")
    private JdbcDao adminJdbcDao;

    @Resource(name = "roleDao")
    private RoleDao roleDao;

    @Before
    public void buildUp() {
        JdbcTestUtil.createRole(adminJdbcDao);
    }

    @After
    public void tearDown() {
        JdbcTestUtil.deleteRole(adminJdbcDao);
    }

    @Test
    public void testRequest() {
        Role role = roleDao.request(JdbcTestUtil.ROLEID);
        assertNotNull("request role", role);
    }

    @Test
    public void testRequestAll() {
        List<Role> roles = roleDao.requestAll();
        assertNotNull("request roles", roles);
        assertTrue("roles found", roles.size() > 0);
    }
}

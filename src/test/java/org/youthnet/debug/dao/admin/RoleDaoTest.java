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
import org.youthnet.debug.util.test.AdminJdbcTestUtil;
import org.youthnet.debug.domain.admin.Role;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: karl
 * Date: 19-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/admin-test-config.xml",
        "/config/spring/admin/admin-hibernate-config.xml",
        "/config/spring/admin/admin-jdbc-config.xml"})
public class RoleDaoTest {

    private static final Log log = LogFactory.getLog(RoleDaoTest.class);

    @Resource(name = "adminJdbcTestUtil")
    private AdminJdbcTestUtil adminJdbcTestUtil;

    @Resource(name = "roleDao")
    private RoleDao roleDao;

    @Before
    public void buildUp() {
        adminJdbcTestUtil.createRole();
    }

    @After
    public void tearDown() {
        adminJdbcTestUtil.deleteRole();
    }

    @Test
    public void testRequest() {
        Role role = roleDao.request(adminJdbcTestUtil.getROLEID());
        assertNotNull("request role", role);
    }

    @Test
    public void testRequestAll() {
        List<Role> roles = roleDao.requestAll();
        assertNotNull("request roles", roles);
        assertTrue("roles found", roles.size() > 0);
    }
}

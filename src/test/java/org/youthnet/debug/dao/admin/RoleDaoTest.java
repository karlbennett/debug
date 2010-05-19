package org.youthnet.debug.dao.admin;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.youthnet.debug.domain.admin.Role;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: karl
 * Date: 19-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config/spring/adminApplicationContext.xml"})
public class RoleDaoTest {

    @Resource(name = "roleDao")
    private RoleDao roleDao;

    private static final Long ROLEID = new Long(-1);

    @Test
    public void testRequest() {
        Role role = roleDao.request(ROLEID);
        assertNotNull("request role", role);
    }

    @Test
    public void testRequestAll() {
        List<Role> roles = roleDao.requestAll();
        assertNotNull("request roles", roles);
        assertTrue("roles found", roles.size() > 0);
    }
}

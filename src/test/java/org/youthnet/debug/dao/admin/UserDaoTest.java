package org.youthnet.debug.dao.admin;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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
@ContextConfiguration(locations = {"/config/spring/admin-hibernate-config.xml"})
public class UserDaoTest {

    @Resource(name = "userDao")
    private UserDao userDao;

    public static final String USERID = "11111111-1111-1111-1111-111111111111";

    @Test
    public void testRequest() {
        User user = userDao.request(UuidTypeImpl.fromString(USERID));
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

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
import org.youthnet.debug.domain.admin.User;
import org.youthnet.debug.domain.admin.Vuo;
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
@ContextConfiguration(locations = {"/test-config.xml",
        "/config/spring/admin-hibernate-config.xml",
        "/config/spring/admin-jdbc-config.xml"})
public class VuoDaoTest {

    private static final Log log = LogFactory.getLog(VuoDaoTest.class);

    @Resource(name = "jdbcTestUtil")
    JdbcTestUtil jdbcTestUtil;

    @Resource(name = "vuoDao")
    private VuoDao vuoDao;

    @Before
    public void buildUp() {
        jdbcTestUtil.createCollective();
        jdbcTestUtil.createVuo();
        jdbcTestUtil.createUser();
    }

    @After
    public void tearDown() {
        jdbcTestUtil.deleteUser();
        jdbcTestUtil.deleteVuo();
        jdbcTestUtil.deleteCollective();
    }

    @Test
    public void testRequest() {
        Vuo vuo = vuoDao.request(UuidTypeImpl.fromString(jdbcTestUtil.getVUOID()));
        assertNotNull("request vuo", vuo);

        Set<User> users = vuo.getUsers();
        assertNotNull("request users", users);
        assertTrue("users found", users.size() > 0);
    }

    @Test
    public void testRequestAll() {
        List<Vuo> vuos = vuoDao.requestAll();
        assertNotNull("request collectives", vuos);
        assertTrue("collectives found", vuos.size() > 0);
    }
}

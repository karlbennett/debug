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
import org.youthnet.debug.domain.admin.Collective;
import org.youthnet.debug.domain.admin.Vuo;
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
        "/config/spring/admin/admin-hibernate-config.xml",
        "/config/spring/admin/admin-jdbc-config.xml"})
public class CollectiveDaoTest {

    private static final Log log = LogFactory.getLog(CollectiveDaoTest.class);

    @Resource(name = "adminJdbcTestUtil")
    private AdminJdbcTestUtil adminJdbcTestUtil;

    @Resource(name = "collectiveDao")
    private CollectiveDao collectiveDao;

    @Before
    public void buildUp() {
        adminJdbcTestUtil.createCollective();
        adminJdbcTestUtil.createVuo();
    }

    @After
    public void tearDown() {
        adminJdbcTestUtil.deleteVuo();
        adminJdbcTestUtil.deleteCollective();
    }

    @Test
    public void testRequest() {
        Collective collective = collectiveDao.request(UuidTypeImpl.fromString(adminJdbcTestUtil.getCOLLECTIVEID()));
        assertNotNull("request collective", collective);

        Set<Vuo> vuos = collective.getVuos();
        assertNotNull("request vuos", vuos);
        assertTrue("vuos found", vuos.size() > 0);
    }

    @Test
    public void testRequestAll() {
        List<Collective> collectives = collectiveDao.requestAll();
        assertNotNull("request collectives", collectives);
        assertTrue("collectives found", collectives.size() > 0);
    }
}

package org.youthnet.debug.dao.admin;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.youthnet.debug.domain.admin.Vuo;
import org.youthnet.debug.domain.common.impl.UuidTypeImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: karl
 * Date: 19-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/adminApplicationContext.xml"})
public class VuoDaoTest {

    @Resource(name = "vuoDao")
    private VuoDao vuoDao;

    public static final String VUOID = "1d38cd2e-dc06-462c-bbc3-bbe58d1ccc54";

    @Test
    public void testRequest() {
        Vuo vuo = vuoDao.request(UuidTypeImpl.fromString(VUOID));
        assertNotNull("request vuo", vuo);
    }

    @Test
    public void testRequestAll() {
        List<Vuo> vuos = vuoDao.requestAll();
        assertNotNull("request collectives", vuos);
        assertTrue("collectives found", vuos.size() > 0);
    }
}

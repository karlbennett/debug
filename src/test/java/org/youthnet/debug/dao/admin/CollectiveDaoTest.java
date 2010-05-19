package org.youthnet.debug.dao.admin;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.youthnet.debug.domain.admin.Collective;
import org.youthnet.debug.domain.common.impl.UuidTypeImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * User: karl
 * Date: 19-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/adminApplicationContext.xml"})
public class CollectiveDaoTest {

    @Resource(name = "collectiveDao")
    private CollectiveDao collectiveDao;

    public static final String COLLECTIVEID = "10936943-f832-4d33-943a-d087ecfab0f1";

    @Test
    public void testRequest() {
        Collective collective = collectiveDao.request(UuidTypeImpl.fromString(COLLECTIVEID));
        assertNotNull("request collective", collective);
    }

    @Test
    public void testRequestAll() {
        List<Collective> collectives = collectiveDao.requestAll();
        assertNotNull("request collectives", collectives);
        assertTrue("collectives found", collectives.size() > 0);
    }
}

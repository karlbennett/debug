package org.youthnet.debug.dao.jdbc.impl;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.youthnet.debug.dao.jdbc.JdbcDao;

import javax.annotation.Resource;
import java.util.List;


/**
 * User: karl
 * Date: 27-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config/spring/core-hibernate-config.xml",
        "/config/spring/core-data-source-config.xml",
        "/config/spring/core-jdbc-config.xml"})
public class CoreJdbcDaoImplTest {

    @Resource(name = "coreJdbcDaoImpl")
    JdbcDao coreJdbcDao;

    @Test
    public void testGetTableNames() throws Exception {
        List<String> names = coreJdbcDao.getTableNames();

        assertNotNull("names list exist", names);
        assertTrue("names list contains values", names.size() > 0);
    }
}

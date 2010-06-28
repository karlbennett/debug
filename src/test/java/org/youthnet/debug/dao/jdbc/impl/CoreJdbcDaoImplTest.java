package org.youthnet.debug.dao.jdbc.impl;

import static junit.framework.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.youthnet.debug.dao.jdbc.JdbcDao;
import org.youthnet.debug.domain.common.UuidType;
import org.youthnet.debug.domain.common.impl.UuidTypeImpl;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * User: karl
 * Date: 27-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config/spring/core/core-hibernate-config.xml",
        "/config/spring/core/core-jdbc-config.xml"})
public class CoreJdbcDaoImplTest {

    @Resource(name = "coreJdbcDaoImpl")
    JdbcDao coreJdbcDao;

    @Test
    public void testGetTableNames() throws Exception {
        List<String> names = coreJdbcDao.getTableNames();

        assertNotNull("names list exist", names);
        assertTrue("names list contains values", names.size() > 0);
    }

    @Ignore
    @Test
    public void testGetRowById() throws Exception {
        Map<String, Object> row = coreJdbcDao.getRowById(UuidTypeImpl.fromString("16d4058a-feb3-4ac0-9ed6-d519961c7dc1"));
        assertNotNull("get row by id", row);
        assertTrue("row by id exists", row.size() > 0);
        assertTrue("row id converted", row.get("ID") instanceof UuidType);
    }
}

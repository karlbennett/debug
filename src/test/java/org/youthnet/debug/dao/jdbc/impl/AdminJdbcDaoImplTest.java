package org.youthnet.debug.dao.jdbc.impl;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.youthnet.debug.dao.jdbc.JdbcDao;
import org.youthnet.debug.dao.util.JdbcTestUtil;
import org.youthnet.debug.domain.common.UuidType;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * User: karl
 * Date: 25-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-config.xml",
        "/config/spring/admin-hibernate-config.xml",
        "/config/spring/admin-jdbc-config.xml"})
public class AdminJdbcDaoImplTest {

    @Resource(name = "jdbcTestUtil")
    private JdbcTestUtil jdbcTestUtil;

    @Resource(name = "adminJdbcDaoImpl")
    private JdbcDao adminJdbcDao;

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
    public void testGetTableNames() throws Exception {
        List<String> names = adminJdbcDao.getTableNames();

        assertNotNull("names list exist", names);
        assertTrue("names list contains values", names.size() > 0);
    }

    @Test
    public void testGetTableRows() throws Exception {
        List<Map <String, Object>> rows = adminJdbcDao.getTableRows("vuo");
        assertNotNull("get table rows", rows);
        assertTrue("table rows exist", rows.size() > 0);
        assertTrue("table rows id converted", rows.get(0).get("ID") instanceof UuidType);
    }

    @Test
    public void testGetRowById() throws Exception {
        Map <String, Object> row = adminJdbcDao.getRowById(jdbcTestUtil.getVUOID());
        assertNotNull("get row by id", row);
        assertTrue("row by id exists", row.size() > 0);
        assertTrue("row id converted", row.get("ID") instanceof UuidType);
        assertEquals("correct vuo name", jdbcTestUtil.getVUONAME(), row.get("NAME"));
        assertEquals("correct vuo code", jdbcTestUtil.getVUOCODE(), row.get("VUOCODE"));
        assertNotNull("table name added", row.get("tableName"));
        assertEquals("table name correct", "VUO", row.get("tableName"));
    }
}

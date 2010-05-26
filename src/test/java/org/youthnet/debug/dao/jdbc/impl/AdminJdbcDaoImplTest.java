package org.youthnet.debug.dao.jdbc.impl;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.youthnet.debug.dao.jdbc.JdbcDao;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * User: karl
 * Date: 25-May-2010
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/config/spring/admin-hibernate-config.xml",
        "/config/spring/admin-jdbc-config.xml"})
public class AdminJdbcDaoImplTest {

    @Resource(name = "adminJdbcDaoImpl")
    private JdbcDao adminJdbcDao;

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

        for(Map<String, Object> row : rows) {
            for(String key : row.keySet()) {
                System.out.println("Column name: " + key + " value: " + row.get(key));
                if( row.get(key) instanceof byte[]) {
                    System.out.println("Value is a byte array.");
                }
                System.out.println();
                System.out.println();
            }
        }
    }
}

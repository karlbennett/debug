package org.youthnet.debug.util;

import static junit.framework.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.youthnet.debug.dao.admin.CollectiveDao;
import org.youthnet.debug.dao.util.JdbcTestUtil;
import org.youthnet.debug.domain.admin.Collective;
import org.youthnet.debug.domain.common.impl.UuidTypeImpl;

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
public class HibernateUtilTest {

    @Resource(name = "jdbcTestUtil")
    private JdbcTestUtil jdbcTestUtil;

    @Resource(name = "&adminSessionFactory")
    AnnotationSessionFactoryBean sessionFactory;

    @Resource(name = "collectiveDao")
    CollectiveDao collectiveDao;

    @Before
    public void buildUp() {
        jdbcTestUtil.createCollective();
        jdbcTestUtil.createVuo();
    }

    @After
    public void tearDown() {
        jdbcTestUtil.deleteVuo();
        jdbcTestUtil.deleteCollective();
    }

    @Test
    public void testGetSchema() throws Exception {
        String [] schema = HibernateUtil.getSchema(sessionFactory);
        assertNotNull("get schema", schema);
        assertTrue("schema contains sql", schema.length > 0);
    }

    @Test
    public void testLogSchema() throws Exception {
        HibernateUtil.logSchema(sessionFactory);    
    }

    @Test
    public void testGetClassForTableName() throws Exception {
        Class clazz = HibernateUtil.getClassForTableName("collective", sessionFactory);
        assertNotNull("get collective class", clazz);
        assertEquals("found collective", clazz.getName(), Collective.class.getName());
    }

    @Test
    public void testCreateRowMap() throws Exception {
        Map<String, Object> row = HibernateUtil.createRowMap(collectiveDao.request(
                UuidTypeImpl.fromString(jdbcTestUtil.getCOLLECTIVEID())));
        assertNotNull("get row", row);
        assertTrue("found row", row.size() > 0);
    }

    @Test
    public void testCreateTableList() throws Exception {
        List<Map<String, Object>> rows = HibernateUtil.createRowList(collectiveDao.requestAll());
        assertNotNull("get rows", rows);
        assertTrue("found rows", rows.size() > 0);
    }

    @Test
    public void testGetDomainTableNames() throws Exception {
        List<String> tableNames = HibernateUtil.getDomainTableNames(sessionFactory);
        assertNotNull("get table names", tableNames);
        assertTrue("table names found", tableNames.size() > 0);
        assertTrue("table names contain collective", tableNames.contains("collective"));
    }

    @Test
    public void testGetTableClassNameForColumnReference() throws Exception {
        String tableClassName = HibernateUtil.getTableClassNameForColumnReference("col_id", sessionFactory);
        assertNotNull("table name found", tableClassName);
        assertEquals("table name correct", "org.youthnet.debug.domain.admin.Collective", tableClassName);

        tableClassName = HibernateUtil.getTableClassNameForColumnReference("vuo_id", sessionFactory);
        assertNotNull("table name found", tableClassName);
        assertEquals("table name correct", "org.youthnet.debug.domain.admin.Vuo", tableClassName);

        tableClassName = HibernateUtil.getTableClassNameForColumnReference("user_id", sessionFactory);
        assertNotNull("table name found", tableClassName);
        assertEquals("table name correct", "org.youthnet.debug.domain.admin.User", tableClassName);
    }

    @Test
    public void testGetTableNameForClass() throws Exception {
        String tabeName = HibernateUtil.getTableNameForClass("org.youthnet.debug.domain.admin.User",
                sessionFactory);
        assertNotNull("user table name found", tabeName);
        assertEquals("user table name correct", "app_user", tabeName);
    }
}

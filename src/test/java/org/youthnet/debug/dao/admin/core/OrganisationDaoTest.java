package org.youthnet.debug.dao.admin.core;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * User: karl
 * Date: 28-Jun-2010
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/admin-test-config.xml",
        "/config/spring/admin/admin-hibernate-config.xml",
        "/config/spring/admin/admin-jdbc-config.xml"})
public class OrganisationDaoTest {

    @Test
    public void testRequestAll() {
        assertTrue(true);
    }

}

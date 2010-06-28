package org.youthnet.debug.dao.admin;

import org.springframework.stereotype.Repository;
import org.youthnet.debug.dao.BaseDao;
import org.youthnet.debug.domain.admin.Role;

/**
 * User: karl
 * Date: 19-May-2010
 */
@Repository("roleDao")
public class RoleDao extends BaseDao<Role, Long> {

    public RoleDao() {
        super(Role.class);
    }
}
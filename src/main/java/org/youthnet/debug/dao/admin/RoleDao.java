package org.youthnet.debug.dao.admin;

import org.springframework.stereotype.Repository;
import org.youthnet.debug.domain.admin.Role;
import org.youthnet.debug.domain.common.UuidType;

/**
 * User: karl
 * Date: 19-May-2010
 */
@Repository("roleDao")
public class RoleDao extends AdminDao<Role, Long> {

    public RoleDao() {
        super(Role.class);
    }
}
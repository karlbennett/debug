package org.youthnet.debug.dao.admin.core;

import org.youthnet.debug.dao.BaseDao;
import org.youthnet.debug.domain.common.UuidType;
import org.youthnet.debug.domain.core.Organisation;

/**
 * User: karl
 * Date: 28-Jun-2010
 */
public class OrganisationDao extends BaseDao<Organisation, UuidType>{
    public OrganisationDao() {
        super(Organisation.class);
    }
}

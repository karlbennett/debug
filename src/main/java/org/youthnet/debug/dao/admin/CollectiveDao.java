package org.youthnet.debug.dao.admin;

import org.springframework.stereotype.Repository;
import org.youthnet.debug.dao.BaseDao;
import org.youthnet.debug.domain.admin.Collective;
import org.youthnet.debug.domain.common.UuidType;

/**
 * User: karl
 * Date: 19-May-2010
 */
@Repository("collectiveDao")
public class CollectiveDao extends BaseDao<Collective, UuidType> {

    public CollectiveDao() {
        super(Collective.class);
    }
}

package org.youthnet.debug.dao.admin;

import org.springframework.stereotype.Repository;
import org.youthnet.debug.domain.admin.Vuo;
import org.youthnet.debug.domain.common.UuidType;

/**
 * User: karl
 * Date: 19-May-2010
 */
@Repository("vuoDao")
public class VuoDao extends AdminDao<Vuo, UuidType> {

    public VuoDao() {
        super(Vuo.class);
    }
}
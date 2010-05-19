package org.youthnet.debug.dao.admin;

import org.springframework.stereotype.Repository;
import org.youthnet.debug.domain.admin.User;
import org.youthnet.debug.domain.common.UuidType;

/**
 * User: karl
 * Date: 19-May-2010
 */
@Repository("userDao")
public class UserDao extends AdminDao<User, UuidType> {

    public UserDao() {
        super(User.class);
    }
}
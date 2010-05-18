package org.youthnet.debug.domain.common;

import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.util.UUID;

/**
 * User: karl
 * Date: 18-May-2010
 */
public interface UuidType extends UserType, Serializable {

    public void setUuid(UUID uuid);

    public UUID getUuid();
}

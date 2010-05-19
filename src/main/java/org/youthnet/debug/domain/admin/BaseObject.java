package org.youthnet.debug.domain.admin;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;
import org.youthnet.debug.domain.common.UuidType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User: karl
 * Date: 18-May-2010
 */
@MappedSuperclass
public class BaseObject implements Serializable {

    @Id
    @Column(columnDefinition = "raw(16)")
    @Type(type = "org.youthnet.debug.domain.common.impl.UuidTypeImpl")
    @Generated(GenerationTime.NEVER)
    private UuidType id;

    

    public UuidType getId() {
        return id;
    }

    public void setId(UuidType id) {
        this.id = id;
    }
}
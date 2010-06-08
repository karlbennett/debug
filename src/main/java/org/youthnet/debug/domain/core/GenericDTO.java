package org.youthnet.debug.domain.core;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;
import org.youthnet.debug.domain.common.UuidType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Olivier Van Acker (olivier.van.acker@youthnet.org)
 * @since May 21, 2009
 */
@MappedSuperclass
public abstract class GenericDTO implements Serializable {

    private UuidType id;
    private int version;
    private Date created;
    private Date modified;
    private UuidType modifiedBy;
    private boolean deleted;

    @Id
    @Column(columnDefinition = "raw(16)")
    @Type(type = "org.youthnet.debug.domain.common.impl.UuidTypeImpl")
    @Generated(GenerationTime.NEVER)
    public UuidType getId() {
        return this.id;
    }

    public void setId(UuidType id) {
        this.id = id;
    }

    @Version
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @Column(columnDefinition = "raw(16)")
    @Type(type = "org.youthnet.debug.domain.common.impl.UuidTypeImpl")
    public UuidType getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UuidType modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Column
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

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

    @Id
    @Column(columnDefinition = "raw(16)")
    @Type(type = "org.youthnet.debug.domain.common.impl.UuidTypeImpl")
    @Generated(GenerationTime.NEVER)
    private UuidType id;

    @Version
    private int version;

    @Column
    private Date created;

    @Column
    private Date modified;

    @Column(columnDefinition = "raw(16)")
    @Type(type = "org.youthnet.debug.domain.common.impl.UuidTypeImpl")
    private UuidType modifiedBy;

    @Column
    private boolean deleted;

    public UuidType getId() {
        return this.id;
    }

    public void setId(UuidType id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public UuidType getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UuidType modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

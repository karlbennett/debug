package org.youthnet.debug.domain.core;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import org.hibernate.annotations.Type;
import org.youthnet.debug.domain.common.UuidType;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.*;

/**
 * SearchQueries generated by hbm2java
 */
@Entity
@Table(name = "SearchQueries")
public class SearchQueries extends GenericDTO implements java.io.Serializable {


    private Blob data;
    private boolean isEditable;
    private UuidType userId;
    private Date lastUpdated;

    public SearchQueries() {
    }

    public SearchQueries(Blob data, boolean isEditable, UuidType userId, Date lastUpdated) {
        this.data = data;
        this.isEditable = isEditable;
        this.userId = userId;
        this.lastUpdated = lastUpdated;
    }

    @Lob
    public Blob getData() {
        return this.data;
    }

    public void setData(Blob data) {
        this.data = data;
    }

    @Column
    public boolean isIsEditable() {
        return this.isEditable;
    }

    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Column(columnDefinition = "raw(16)")
    @Type(type = "org.youthnet.debug.domain.common.impl.UuidTypeImpl")
    public UuidType getUserId() {
        return this.userId;
    }

    public void setUserId(UuidType userId) {
        this.userId = userId;
    }

    @Column
    public Date getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


}



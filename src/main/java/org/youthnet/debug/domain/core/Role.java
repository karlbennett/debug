package org.youthnet.debug.domain.core;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import org.hibernate.annotations.Type;
import org.youthnet.debug.domain.common.UuidType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Role generated by hbm2java
 */
@Entity
@Table(name = "Roles")
public class Role extends GenericDTO implements java.io.Serializable {


private Integer vbase2Id;
private UuidType userId;
private String name;
private String description;

    public Role() {
    }

    public Role(Integer vbase2Id, UuidType userId, String name, String description) {
       this.vbase2Id = vbase2Id;
       this.userId = userId;
       this.name = name;
       this.description = description;
    }
   
    
    @Column
    public Integer getVbase2Id() {
        return this.vbase2Id;
    }

    public void setVbase2Id(Integer vbase2Id) {
        this.vbase2Id = vbase2Id;
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
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 50)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




}


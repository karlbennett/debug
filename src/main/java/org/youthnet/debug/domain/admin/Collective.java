package org.youthnet.debug.domain.admin;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Type;
import org.youthnet.debug.domain.common.UuidType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User: karl
 * Date: 18-May-2010
 */
@Entity
@Table(name = "collective")
public class Collective {

    @Id
    @Column(columnDefinition = "raw(16)")
    @Type(type = "org.youthnet.debug.domain.common.impl.UuidTypeImpl")
    @Generated(GenerationTime.NEVER)
    private UuidType id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String shortName;

    @Column
    private String password;

    
//    private Set<Vuo> vuos = new HashSet<Vuo>();


    public UuidType getId() {
        return id;
    }

    public void setId(UuidType id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

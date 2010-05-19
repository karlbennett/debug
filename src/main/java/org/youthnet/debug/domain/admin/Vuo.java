package org.youthnet.debug.domain.admin;

import org.hibernate.annotations.Type;
import org.youthnet.debug.domain.common.UuidType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: karl
 * Date: 18-May-2010
 */
@Entity
@Table(name = "vuo")
public class Vuo extends BaseObject {

    @Column(unique = true)
    private String name;
    
    @Column(unique = true)
    private String vuoCode;

//    private Collective collective;

    @Column(columnDefinition = "raw(16)")
    @Type(type = "org.youthnet.debug.domain.common.impl.UuidTypeImpl")
    private UuidType organisationId;
//    private Set<User> users = new HashSet<User>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVuoCode() {
        return vuoCode;
    }

    public void setVuoCode(String vuoCode) {
        this.vuoCode = vuoCode;
    }

    public UuidType getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(UuidType organisationId) {
        this.organisationId = organisationId;
    }
}
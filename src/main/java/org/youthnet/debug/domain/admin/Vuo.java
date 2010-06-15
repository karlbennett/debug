package org.youthnet.debug.domain.admin;

import org.hibernate.annotations.Type;
import org.youthnet.debug.domain.common.UuidType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn(name = "col_id", columnDefinition = "raw(16)")
    private Collective collective;

    @Column(columnDefinition = "raw(16)")
    @Type(type = "org.youthnet.debug.domain.common.impl.UuidTypeImpl")
    private UuidType organisationId;

    @OneToMany( mappedBy = "vuo", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private Set<User> users = new HashSet<User>();


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

    public Collective getCollective() {
        return collective;
    }

    public void setCollective(Collective collective) {
        this.collective = collective;
    }

    public UuidType getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(UuidType organisationId) {
        this.organisationId = organisationId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
package org.youthnet.debug.domain.admin;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * User: karl
 * Date: 18-May-2010
 */
@Entity
@Table(name = "collective")
public class Collective extends BaseObject {

    @Column(unique = true)
    private String name;
    
    @Column(unique = true)
    private String shortName;

    @Column
    private String password;

    @OneToMany( mappedBy = "collective", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    private Set<Vuo> vuos = new HashSet<Vuo>();

    
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

    public Set<Vuo> getVuos() {
        return vuos;
    }

    public void setVuos(Set<Vuo> vuos) {
        this.vuos = vuos;
    }
}

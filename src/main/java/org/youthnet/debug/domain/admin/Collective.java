package org.youthnet.debug.domain.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

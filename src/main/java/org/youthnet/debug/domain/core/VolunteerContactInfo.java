package org.youthnet.debug.domain.core;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import org.youthnet.debug.domain.core.GenericDTO;

import javax.persistence.*;

/**
 * VolunteerContactInfo generated by hbm2java
 */
@Entity
@Table(name = "VolunteerContactInfo")
@Inheritance(strategy = InheritanceType.JOINED)
public class VolunteerContactInfo extends GenericDTO implements java.io.Serializable {

    @Column
    private boolean isActive;

    @Column
    private boolean isDefault;

    @Column
    private String notes;

    public VolunteerContactInfo() {
    }

    public VolunteerContactInfo(boolean isActive, boolean isDefault, String notes) {
        this.isActive = isActive;
        this.isDefault = isDefault;
        this.notes = notes;
    }

    public boolean isIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


}



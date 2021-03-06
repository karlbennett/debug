package org.youthnet.debug.domain.core.lookups;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import org.youthnet.debug.domain.common.UuidType;
import org.youthnet.debug.domain.core.lookups.LookupBase;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * ActivityType generated by hbm2java
 */

@Entity
@DiscriminatorValue(value = "ActivityType")
public class ActivityType extends LookupBase implements java.io.Serializable {

    @Column
    private boolean appliesToVolunteers;

    @Column
    private boolean appliesToOrganisations;

    @Column
    private boolean appliesToOpportunities;

    public ActivityType() {
    }

    public ActivityType(Integer vBase2Id, UuidType languageId, String value, boolean isActive, Integer sortOrder, boolean isUserEditable, UuidType ownerId, boolean appliesToVolunteers, boolean appliesToOrganisations, boolean appliesToOpportunities) {
        super(vBase2Id, languageId, value, isActive, sortOrder, isUserEditable, ownerId);
        this.appliesToVolunteers = appliesToVolunteers;
        this.appliesToOrganisations = appliesToOrganisations;
        this.appliesToOpportunities = appliesToOpportunities;
    }


    public boolean isAppliesToVolunteers() {
        return this.appliesToVolunteers;
    }

    public void setAppliesToVolunteers(boolean appliesToVolunteers) {
        this.appliesToVolunteers = appliesToVolunteers;
    }

    public boolean isAppliesToOrganisations() {
        return this.appliesToOrganisations;
    }

    public void setAppliesToOrganisations(boolean appliesToOrganisations) {
        this.appliesToOrganisations = appliesToOrganisations;
    }

    public boolean isAppliesToOpportunities() {
        return this.appliesToOpportunities;
    }

    public void setAppliesToOpportunities(boolean appliesToOpportunities) {
        this.appliesToOpportunities = appliesToOpportunities;
    }
}



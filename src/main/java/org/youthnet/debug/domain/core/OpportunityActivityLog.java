package org.youthnet.debug.domain.core;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import org.hibernate.annotations.Cascade;

import javax.persistence.*;

/**
 * OpportunityActivityLog generated by hbm2java
 */
@Entity
@Table(name = "OpportunityActLogs")
public class OpportunityActivityLog  extends GenericDTO implements java.io.Serializable {


private Opportunity opportunity;
private ActivityLog activityLog;
private LocationBase opportunityLocation;

    public OpportunityActivityLog() {
    }

    public OpportunityActivityLog(Opportunity opportunity, ActivityLog activityLog, LocationBase opportunityLocation) {
       this.opportunity = opportunity;
       this.activityLog = activityLog;
       this.opportunityLocation = opportunityLocation;
    }
   
    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.Opportunity.class,
            fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "OpportunityId", columnDefinition = "raw(16)")
    public Opportunity getOpportunity() {
        return this.opportunity;
    }

    public void setOpportunity(Opportunity opportunity) {
        this.opportunity = opportunity;
    }

    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.ActivityLog.class,
            fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "ActivityLogId", columnDefinition = "raw(16)")
    @javax.xml.bind.annotation.XmlTransient
    public ActivityLog getActivityLog() {
        return this.activityLog;
    }

    public void setActivityLog(ActivityLog activityLog) {
        this.activityLog = activityLog;
    }

    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.LocationBase.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "OpportunityLocationId", columnDefinition = "raw(16)")
    public LocationBase getOpportunityLocation() {
        return this.opportunityLocation;
    }

    public void setOpportunityLocation(LocationBase opportunityLocation) {
        this.opportunityLocation = opportunityLocation;
    }




}



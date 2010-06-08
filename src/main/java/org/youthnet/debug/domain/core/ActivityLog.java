package org.youthnet.debug.domain.core;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.ForeignKey;
import org.youthnet.debug.domain.core.lookups.ActivityType;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * ActivityLog generated by hbm2java
 */
@Entity
@Table(name = "ActivityLogs")
public class ActivityLog extends GenericDTO implements java.io.Serializable {


private Integer vbase2Id;
private Date startDate;
private Date endDate;
private boolean isAllDayEvent;
private boolean showInCalender;
private String startTime;
private String endTime;
private String subject;
private String notes;
private ActivityType activityType;
private OrganisationContact personHere;
private Set<VolunteerActivityLog> volunteerActivityLogs = new HashSet<VolunteerActivityLog>(0);
private Set<OrganisationActivityLog> organisationActivityLogs = new HashSet<OrganisationActivityLog>(0);
private Set<OpportunityActivityLog> opportunityActivityLogs = new HashSet<OpportunityActivityLog>(0);

    public ActivityLog() {
    }

    public ActivityLog(Integer vbase2Id, Date startDate, Date endDate, boolean isAllDayEvent, boolean showInCalender, String startTime, String endTime, String subject, String notes, ActivityType activityType, OrganisationContact personHere, Set<VolunteerActivityLog> volunteerActivityLogs, Set<OrganisationActivityLog> organisationActivityLogs, Set<OpportunityActivityLog> opportunityActivityLogs) {
       this.vbase2Id = vbase2Id;
       this.startDate = startDate;
       this.endDate = endDate;
       this.isAllDayEvent = isAllDayEvent;
       this.showInCalender = showInCalender;
       this.startTime = startTime;
       this.endTime = endTime;
       this.subject = subject;
       this.notes = notes;
       this.activityType = activityType;
       this.personHere = personHere;
       this.volunteerActivityLogs = volunteerActivityLogs;
       this.organisationActivityLogs = organisationActivityLogs;
       this.opportunityActivityLogs = opportunityActivityLogs;
    }
   
    @Column
    public Integer getVbase2Id() {
        return this.vbase2Id;
    }

    public void setVbase2Id(Integer vbase2Id) {
        this.vbase2Id = vbase2Id;
    }

    @Column
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column
    public boolean isIsAllDayEvent() {
        return this.isAllDayEvent;
    }

    public void setIsAllDayEvent(boolean isAllDayEvent) {
        this.isAllDayEvent = isAllDayEvent;
    }

    @Column
    public boolean isShowInCalender() {
        return this.showInCalender;
    }

    public void setShowInCalender(boolean showInCalender) {
        this.showInCalender = showInCalender;
    }

    @Column(length = 5)
    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Column(length = 5)
    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Column(length = 255)
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.lookups.ActivityType.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "ActivityTypeId", columnDefinition = "raw(16)")
    public ActivityType getActivityType() {
        return this.activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.OrganisationContact.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "PersonHereId", columnDefinition = "raw(16)")
    public OrganisationContact getPersonHere() {
        return this.personHere;
    }

    public void setPersonHere(OrganisationContact personHere) {
        this.personHere = personHere;
    }

    @OneToMany(targetEntity = org.youthnet.debug.domain.core.VolunteerActivityLog.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @ForeignKey(name = "FK_VolunteerActivityLogs")    
    @JoinColumn(name = "ActivityLogId",
            columnDefinition = "raw(16)")
    public Set<VolunteerActivityLog> getVolunteerActivityLogs() {
        return this.volunteerActivityLogs;
    }

    public void setVolunteerActivityLogs(Set<VolunteerActivityLog> volunteerActivityLogs) {
        this.volunteerActivityLogs = volunteerActivityLogs;
    }

    @OneToMany(targetEntity = org.youthnet.debug.domain.core.OrganisationActivityLog.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @ForeignKey(name = "FK_OrganisationActivityLogs")
    @JoinColumn(name = "ActivityLogId",
            columnDefinition = "raw(16)")
    public Set<OrganisationActivityLog> getOrganisationActivityLogs() {
        return this.organisationActivityLogs;
    }

    public void setOrganisationActivityLogs(Set<OrganisationActivityLog> organisationActivityLogs) {
        this.organisationActivityLogs = organisationActivityLogs;
    }

    @OneToMany(targetEntity = org.youthnet.debug.domain.core.OpportunityActivityLog.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @ForeignKey(name = "FK_OpportunityActivityLogs")
    @JoinColumn(name = "ActivityLogId",
            columnDefinition = "raw(16)")
    public Set<OpportunityActivityLog> getOpportunityActivityLogs() {
        return this.opportunityActivityLogs;
    }

    public void setOpportunityActivityLogs(Set<OpportunityActivityLog> opportunityActivityLogs) {
        this.opportunityActivityLogs = opportunityActivityLogs;
    }




}



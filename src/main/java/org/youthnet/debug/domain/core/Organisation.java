package org.youthnet.debug.domain.core;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.*;
import org.youthnet.debug.domain.core.lookups.CampaignsAndInitiatives;
import org.youthnet.debug.domain.core.lookups.CauseInterest;
import org.youthnet.debug.domain.core.lookups.GeographicalArea;
import org.youthnet.debug.domain.core.lookups.HowHeard;
import org.youthnet.debug.domain.core.lookups.OrganisationType;
import org.youthnet.debug.domain.core.lookups.TaggedData;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Organisation generated by hbm2java
 */
@Entity
@Table(name = "Organisations")
public class Organisation extends GenericDTO implements java.io.Serializable {


    private Integer vbase2Id;
    private boolean isVuo;
    private String name;
    private String ownId;
    private String registeredCharityNumber;
    private Set<OrganisationAddress> organisationAddresses = new HashSet<OrganisationAddress>(0);
    private String missionStatement;
    private String description;
    private String openingHours;
    private Set<CauseInterest> causesInterests = new HashSet<CauseInterest>(0);
    private Set<OrganisationType> types = new HashSet<OrganisationType>(0);
    private Set<GeographicalArea> organisationGeographicalAreas = new HashSet<GeographicalArea>(0);
    private HowHeard howHeard;
    private String howHeardDetails;
    private Set<NewPolicyEntry> orgNewPolicyEntries = new HashSet<NewPolicyEntry>(0);
    private Set<QualityStdsAccrPolicyEntry> qualityStdsAccrPolicyEntries = new HashSet<QualityStdsAccrPolicyEntry>(0);
    private Set<TaggedData> tags = new HashSet<TaggedData>(0);
    private Set<CampaignsAndInitiatives> campaignsAndInitiatives = new HashSet<CampaignsAndInitiatives>(0);
    private boolean isActive;

    public Organisation() {
    }


    public Organisation(String name) {
        this.name = name;
    }

    public Organisation(Integer vbase2Id, boolean isVuo, String name, String ownId, String registeredCharityNumber, Set<OrganisationAddress> organisationAddresses, String missionStatement, String description, String openingHours, Set<CauseInterest> causesInterests, Set<OrganisationType> types, Set<GeographicalArea> organisationGeographicalAreas, HowHeard howHeard, String howHeardDetails, Set<NewPolicyEntry> orgNewPolicyEntries, Set<QualityStdsAccrPolicyEntry> qualityStdsAccrPolicyEntries, Set<TaggedData> tags, Set<CampaignsAndInitiatives> campaignsAndInitiatives) {
        this.vbase2Id = vbase2Id;
        this.isVuo = isVuo;
        this.name = name;
        this.ownId = ownId;
        this.registeredCharityNumber = registeredCharityNumber;
        this.organisationAddresses = organisationAddresses;
        this.missionStatement = missionStatement;
        this.description = description;
        this.openingHours = openingHours;
        this.causesInterests = causesInterests;
        this.types = types;
        this.organisationGeographicalAreas = organisationGeographicalAreas;
        this.howHeard = howHeard;
        this.howHeardDetails = howHeardDetails;
        this.orgNewPolicyEntries = orgNewPolicyEntries;
        this.qualityStdsAccrPolicyEntries = qualityStdsAccrPolicyEntries;
        this.tags = tags;
        this.campaignsAndInitiatives = campaignsAndInitiatives;
    }

    @Column
    public Integer getVbase2Id() {
        return this.vbase2Id;
    }

    public void setVbase2Id(Integer vbase2Id) {
        this.vbase2Id = vbase2Id;
    }

    @Column
    @Index(name = "OrgIsVuo")
    public boolean isIsVuo() {
        return this.isVuo;
    }

    public void setIsVuo(boolean isVuo) {
        this.isVuo = isVuo;
    }

    @Column(nullable = false)
    @Index(name = "OrgName")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    @Index(name = "OrgOwnId")
    public String getOwnId() {
        return this.ownId;
    }

    public void setOwnId(String ownId) {
        this.ownId = ownId;
    }

    @Column(length = 50)
    @Index(name = "OrgRegCharNum")
    public String getRegisteredCharityNumber() {
        return this.registeredCharityNumber;
    }

    public void setRegisteredCharityNumber(String registeredCharityNumber) {
        this.registeredCharityNumber = registeredCharityNumber;
    }

    @OneToMany(targetEntity = org.youthnet.debug.domain.core.OrganisationAddress.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinColumn(name = "OrgAddConInfoId")
    public Set<OrganisationAddress> getOrganisationAddresses() {
        return this.organisationAddresses;
    }

    public void setOrganisationAddresses(Set<OrganisationAddress> organisationAddresses) {
        this.organisationAddresses = organisationAddresses;
    }

    @Column(length = 2000)
    public String getMissionStatement() {
        return this.missionStatement;
    }

    public void setMissionStatement(String missionStatement) {
        this.missionStatement = missionStatement;
    }

    @Column(length = 2000)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public String getOpeningHours() {
        return this.openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    @ManyToMany(targetEntity = org.youthnet.debug.domain.core.lookups.CauseInterest.class, fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @JoinTable(name = "OrganisationCausesInterests",
            joinColumns = @JoinColumn(name = "OrganisationId", columnDefinition = "raw(16)"),
            inverseJoinColumns = @JoinColumn(name = "LookupId", columnDefinition = "raw(16)"))
    @Where(clause = "Discriminator='CauseInterest'")
    public Set<CauseInterest> getCausesInterests() {
        return this.causesInterests;
    }

    public void setCausesInterests(Set<CauseInterest> causesInterests) {
        this.causesInterests = causesInterests;
    }

    @ManyToMany(targetEntity = org.youthnet.debug.domain.core.lookups.OrganisationType.class, fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @JoinTable(name = "OrganisationOrganisationType",
            joinColumns = @JoinColumn(name = "OrganisationId", columnDefinition = "raw(16)"),
            inverseJoinColumns = @JoinColumn(name = "LookupId", columnDefinition = "raw(16)"))
    @Where(clause = "Discriminator='OrganisationType'")
    public Set<OrganisationType> getTypes() {
        return this.types;
    }

    public void setTypes(Set<OrganisationType> types) {
        this.types = types;
    }

    @ManyToMany(targetEntity = org.youthnet.debug.domain.core.lookups.GeographicalArea.class, fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @JoinTable(name = "OrganisationGeographicalArea",
            joinColumns = @JoinColumn(name = "OrganisationId", columnDefinition = "raw(16)"),
            inverseJoinColumns = @JoinColumn(name = "LookupId", columnDefinition = "raw(16)"))
    @Where(clause = "Discriminator='GeographicalArea'")
    public Set<GeographicalArea> getOrganisationGeographicalAreas() {
        return this.organisationGeographicalAreas;
    }

    public void setOrganisationGeographicalAreas(Set<GeographicalArea> organisationGeographicalAreas) {
        this.organisationGeographicalAreas = organisationGeographicalAreas;
    }

    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.lookups.HowHeard.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "HowHeardId", columnDefinition = "raw(16)")
    public HowHeard getHowHeard() {
        return this.howHeard;
    }

    public void setHowHeard(HowHeard howHeard) {
        this.howHeard = howHeard;
    }

    @Column
    public String getHowHeardDetails() {
        return this.howHeardDetails;
    }

    public void setHowHeardDetails(String howHeardDetails) {
        this.howHeardDetails = howHeardDetails;
    }

    @ManyToMany(targetEntity = org.youthnet.debug.domain.core.NewPolicyEntry.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinTable(name = "OrganisationPoliciesAndProc",
            joinColumns = @JoinColumn(name = "OwnerId", columnDefinition = "raw(16)"),
            inverseJoinColumns = @JoinColumn(name = "PolicyId", columnDefinition = "raw(16)"))
    @Where(clause = "Discriminator='P'")
    public Set<NewPolicyEntry> getOrgNewPolicyEntries() {
        return this.orgNewPolicyEntries;
    }

    public void setOrgNewPolicyEntries(Set<NewPolicyEntry> orgNewPolicyEntries) {
        this.orgNewPolicyEntries = orgNewPolicyEntries;
    }

    @ManyToMany(targetEntity = org.youthnet.debug.domain.core.QualityStdsAccrPolicyEntry.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinTable(name = "OrganisaitonQualityStdsAccr",
            joinColumns = @JoinColumn(name = "OwnerId", columnDefinition = "raw(16)"),
            inverseJoinColumns = @JoinColumn(name = "PolicyId", columnDefinition = "raw(16)"))
    @Where(clause = "Discriminator='Q'")
    public Set<QualityStdsAccrPolicyEntry> getQualityStdsAccrPolicyEntries() {
        return this.qualityStdsAccrPolicyEntries;
    }

    public void setQualityStdsAccrPolicyEntries(Set<QualityStdsAccrPolicyEntry> qualityStdsAccrPolicyEntries) {
        this.qualityStdsAccrPolicyEntries = qualityStdsAccrPolicyEntries;
    }

    @ManyToMany(targetEntity = org.youthnet.debug.domain.core.lookups.TaggedData.class, fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @JoinTable(name = "OrganisationTaggedData",
            joinColumns = @JoinColumn(name = "OrganisationId", columnDefinition = "raw(16)"),
            inverseJoinColumns = @JoinColumn(name = "LookupId", columnDefinition = "raw(16)"))
    @Where(clause = "Discriminator='TaggedData'")
    public Set<TaggedData> getTags() {
        return this.tags;
    }

    public void setTags(Set<TaggedData> tags) {
        this.tags = tags;
    }

    @ManyToMany(targetEntity = org.youthnet.debug.domain.core.lookups.CampaignsAndInitiatives.class, fetch = FetchType.EAGER)
    @Fetch( value = org.hibernate.annotations.FetchMode.SELECT)
    @JoinTable(name = "OrganisationCampsAndInits",
            joinColumns = @JoinColumn(name = "OrganisationId", columnDefinition = "raw(16)"),
            inverseJoinColumns = @JoinColumn(name = "LookupId", columnDefinition = "raw(16)"))
    @Where(clause = "Discriminator='CampaignsAndInitiatives'")
    public Set<CampaignsAndInitiatives> getCampaignsAndInitiatives() {
        return this.campaignsAndInitiatives;
    }

    public void setCampaignsAndInitiatives(Set<CampaignsAndInitiatives> campaignsAndInitiatives) {
        this.campaignsAndInitiatives = campaignsAndInitiatives;
    }

    @Column
    public boolean isIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}


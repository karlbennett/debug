package org.youthnet.debug.domain.core;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import java.util.Set;
import org.youthnet.debug.domain.core.enums.LocationTypes;
import org.youthnet.debug.domain.core.enums.PublicContactDetailsSource;
import org.youthnet.debug.domain.core.lookups.GeographicalArea;
import org.youthnet.debug.domain.core.lookups.Region;

import javax.persistence.*;

/**
 * RegionLocationAddress generated by hbm2java
 */
@Entity
@DiscriminatorValue(value = "RegionLocationAddress")
public class RegionLocationAddress extends LocationBase implements java.io.Serializable {


private Region region;

    public RegionLocationAddress() {
        this.setLocationType(LocationTypes.REGION);
    }

    public RegionLocationAddress(String DisplayString, Opportunity owner, LocationTypes locationType, ContactDetails contactDetails, ContactDetails publicContactDetails, boolean UseCustomOrgName, String CustomOrgName, PublicContactDetailsSource publicContactDetailsSource, Set<GeographicalArea> geographicalAreas, boolean IsActive, Region region) {
        super(DisplayString, owner, locationType, contactDetails, publicContactDetails, UseCustomOrgName, CustomOrgName, publicContactDetailsSource, geographicalAreas, IsActive);        
       this.region = region;
    }
   
    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.lookups.Region.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "RegionId", columnDefinition = "raw(16)")
    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }




}


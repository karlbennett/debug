package org.youthnet.debug.domain.core.lookups;


import org.youthnet.debug.domain.common.UuidType;
import org.youthnet.debug.domain.core.lookups.LookupBase;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue(value = "PlacementStatus")
public class PlacementStatus extends LookupBase implements java.io.Serializable {



    public PlacementStatus() {
    }

    public PlacementStatus(Integer vBase2Id, UuidType languageId, String value, boolean isActive, Integer sortOrder, boolean isUserEditable, UuidType ownerId) {
        super(vBase2Id, languageId, value, isActive, sortOrder, isUserEditable, ownerId);
    }
}
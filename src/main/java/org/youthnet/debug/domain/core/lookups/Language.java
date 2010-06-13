package org.youthnet.debug.domain.core.lookups;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.youthnet.debug.domain.common.UuidType;
import org.youthnet.debug.domain.core.lookups.LookupBase;

/**
 * Language generated by hbm2java
 */
@Entity
@DiscriminatorValue(value = "Language")
public class Language extends LookupBase implements java.io.Serializable {



    public Language() {
    }

    public Language(Integer vBase2Id, UuidType languageId, String value, boolean isActive, Integer sortOrder, boolean isUserEditable, UuidType ownerId) {
        super(vBase2Id, languageId, value, isActive, sortOrder, isUserEditable, ownerId);        
    }
   




}



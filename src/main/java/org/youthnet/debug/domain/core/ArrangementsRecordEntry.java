package org.youthnet.debug.domain.core;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import org.youthnet.debug.domain.core.lookups.Arrangement;
import org.youthnet.debug.domain.core.lookups.ArrangementStatus;

import javax.persistence.*;

/**
 * ArrangementsRecordEntry generated by hbm2java
 */
@Entity
@DiscriminatorValue(value = "A")
public class ArrangementsRecordEntry extends PolicyEntry implements java.io.Serializable {


private Arrangement policy;
private ArrangementStatus policyStatus;

    public ArrangementsRecordEntry() {
    }

    public ArrangementsRecordEntry(Integer vbase2Id, String comments, Arrangement policy, ArrangementStatus policyStatus) {
        super(vbase2Id, comments);        
       this.policy = policy;
       this.policyStatus = policyStatus;
    }
   
    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.lookups.Arrangement.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "PolicyId", columnDefinition = "raw(16)")
    public Arrangement getPolicy() {
        return this.policy;
    }

    public void setPolicy(Arrangement policy) {
        this.policy = policy;
    }

    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.lookups.ArrangementStatus.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "PolicyStatusId", columnDefinition = "raw(16)")
    public ArrangementStatus getPolicyStatus() {
        return this.policyStatus;
    }

    public void setPolicyStatus(ArrangementStatus policyStatus) {
        this.policyStatus = policyStatus;
    }




}


package org.youthnet.debug.domain.core;
// Generated 14-Dec-2009 11:46:32 by Hibernate Tools 3.2.2.GA


import org.youthnet.debug.domain.core.lookups.Policy;
import org.youthnet.debug.domain.core.lookups.PolicyStatus;

import javax.persistence.*;

/**
 * NewPolicyEntry generated by hbm2java
 */
@Entity
@DiscriminatorValue(value = "P")
public class NewPolicyEntry extends PolicyEntry implements java.io.Serializable {


private Policy policy;
private PolicyStatus policyStatus;

    public NewPolicyEntry() {
    }

    public NewPolicyEntry(Integer vbase2Id, String comments, Policy policy, PolicyStatus policyStatus) {
        super(vbase2Id, comments);        
       this.policy = policy;
       this.policyStatus = policyStatus;
    }
   
    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.lookups.Policy.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "PolicyId", columnDefinition = "raw(16)")
    public Policy getPolicy() {
        return this.policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    @ManyToOne(targetEntity = org.youthnet.debug.domain.core.lookups.PolicyStatus.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "PolicyStatusId", columnDefinition = "raw(16)")
    public PolicyStatus getPolicyStatus() {
        return this.policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }




}


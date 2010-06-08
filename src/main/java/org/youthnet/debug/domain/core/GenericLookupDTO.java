package org.youthnet.debug.domain.core;

import org.youthnet.debug.domain.core.lookups.LookupBase;

/**
 * @author Olivier Van Acker (olivier.van.acker@youthnet.org)
 * @since May 21, 2009
 */
public abstract class GenericLookupDTO extends GenericDTO {

    public abstract LookupBase getLookup();

    public abstract void setLookup(LookupBase lookup);
    
}

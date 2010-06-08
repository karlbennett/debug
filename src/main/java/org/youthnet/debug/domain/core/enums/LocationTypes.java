package org.youthnet.debug.domain.core.enums;

/**
 *
 * @author karl
 */
public enum LocationTypes {

    POSTCODE(0),
    COUNTY(1),
    REGION(2);
    
    private int id;

    private LocationTypes(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

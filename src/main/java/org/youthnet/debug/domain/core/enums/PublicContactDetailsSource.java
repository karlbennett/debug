package org.youthnet.debug.domain.core.enums;

/**
 *
 * @author karl
 */
public enum PublicContactDetailsSource {

    REUSECONTACTDETAILSADDRESS(0),
    REUSECONTACTDETAILSPERSON(1),
    USEFULLCONTACTDETAILS(2),
    SPECIFYDIFFERENTPUBLICCONTACTDETAILS(3),
    USINGSHAREDCONTACTDETAILS(4);
    
    private int id;

    private PublicContactDetailsSource(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

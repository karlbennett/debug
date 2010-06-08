package org.youthnet.debug.domain.core.enums;

/**
 *
 * @author karl
 */
public enum ContactDetailsSource {
    PERSON(1),
    ADDRESS(2),
    CUSTOM(3);

    private int id;

    private ContactDetailsSource(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

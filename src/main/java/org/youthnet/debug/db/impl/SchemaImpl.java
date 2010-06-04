package org.youthnet.debug.db.impl;

import org.youthnet.debug.db.Schema;

/**
 * User: karl
 * Date: 04-Jun-2010
 */
public class SchemaImpl implements Schema {

    private String name;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}

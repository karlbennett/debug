package org.youthnet.debug.db.bean.session.impl;

import org.youthnet.debug.db.bean.session.Schema;

/**
 * User: karl
 * Date: 04-Jun-2010
 *
 * An interface has been created for this bean so that it can be proxied with the "'standard' JDK interface-based proxies".
 * http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/htmlsingle/spring-framework-reference.html#beans-factory-scopes-other-injection-proxies
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

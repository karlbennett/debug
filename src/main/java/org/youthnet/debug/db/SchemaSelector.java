package org.youthnet.debug.db;

/**
 * User: karl
 * Date: 02-Jun-2010
 */
public class SchemaSelector {

    private static final ThreadLocal<String> schemaName = new ThreadLocal<String>();

    public static void setSchema(String schema) {
        schemaName.set(schema);
    }

    public static String getSchema() {
        return schemaName.get();
    }
}

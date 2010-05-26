package org.youthnet.debug.util;

/**
 * User: Karl
 * Date: 23-May-2010
 */
public class SqlSyntaxUtil {
    private String sqlTrue;
    private String sqlFalse;
    private String binTypeStart;
    private String binTypeEnd;
    private String schema;
    private String adminSchema;

    public String getSqlTrue() {
        return sqlTrue;
    }

    public void setSqlTrue(String sqlTrue) {
        this.sqlTrue = sqlTrue;
    }

    public String getSqlFalse() {
        return sqlFalse;
    }

    public void setSqlFalse(String sqlFalse) {
        this.sqlFalse = sqlFalse;
    }

    public String getBinTypeStart() {
        return binTypeStart;
    }

    public void setBinTypeStart(String binTypeStart) {
        this.binTypeStart = binTypeStart;
    }

    public String getBinTypeEnd() {
        return binTypeEnd;
    }

    public void setBinTypeEnd(String binTypeEnd) {
        this.binTypeEnd = binTypeEnd;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getAdminSchema() {
        return adminSchema;
    }

    public void setAdminSchema(String adminSchema) {
        this.adminSchema = adminSchema;
    }
}

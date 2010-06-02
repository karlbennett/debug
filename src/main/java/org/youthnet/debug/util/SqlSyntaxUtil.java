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
    // Variable to hold the SQL statement that should be used to select a list of the current schema's table names.
    private String selectTableNamesQuery;
    // Variable to hold the SQL statement that should be used to select a list of the current schema names.
    private String selectSchemaNamesQuery;

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

    public String getSelectTableNamesQuery() {
        return selectTableNamesQuery;
    }

    public void setSelectTableNamesQuery(String selectTableNamesQuery) {
        this.selectTableNamesQuery = selectTableNamesQuery;
    }

    public String getSelectSchemaNamesQuery() {
        return selectSchemaNamesQuery;
    }

    public void setSelectSchemaNamesQuery(String selectSchemaNamesQuery) {
        this.selectSchemaNamesQuery = selectSchemaNamesQuery;
    }
}

package org.youthnet.debug.db;

/**
 * User: karl
 * Date: 02-Jun-2010
 */
public class DbProperties {

    private String domain;
    private String port;
    private String sid;
    private String url;
    private String driver;
    private String defaultCoreUserName;
    private String defaultCorePassword;
    private String defaultSchema;
    private String schemaPrefix;
    private String adminUserName;
    private String adminPassword;
    private String adminSchema;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDefaultCoreUserName() {
        return defaultCoreUserName;
    }

    public void setDefaultCoreUserName(String defaultCoreUserName) {
        this.defaultCoreUserName = defaultCoreUserName;
    }

    public String getDefaultCorePassword() {
        return defaultCorePassword;
    }

    public void setDefaultCorePassword(String defaultCorePassword) {
        this.defaultCorePassword = defaultCorePassword;
    }

    public String getDefaultSchema() {
        return defaultSchema;
    }

    public void setDefaultSchema(String defaultSchema) {
        this.defaultSchema = defaultSchema;
    }

    public String getSchemaPrefix() {
        return schemaPrefix;
    }

    public void setSchemaPrefix(String schemaPrefix) {
        this.schemaPrefix = schemaPrefix;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminSchema() {
        return adminSchema;
    }

    public void setAdminSchema(String adminSchema) {
        this.adminSchema = adminSchema;
    }
}

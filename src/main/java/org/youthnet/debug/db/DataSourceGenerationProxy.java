package org.youthnet.debug.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.youthnet.debug.util.DbPropertiesUtil;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * User: karl
 * Date: 02-Jun-2010
 *
 * This class is injected within the Spring context files. This allows the class to be injected differently depending on different profiles.
 * Also it allows the one class to be used to instantiated with different attributes without having to create sepurate sub classes.
 */
public class DataSourceGenerationProxy extends AbstractRoutingDataSource {

    private static final Log log = LogFactory.getLog(DataSourceGenerationProxy.class);

    // Bean to hold the schema name that should be used to choose the right data source.
    // This should be a session variable so that it is unique to each user.
    private Schema schema;

    // Bean to hold all the properties that were used to configure the data sources at start up.
    private DbPropertiesUtil dbPropertiesUtil;

    // Map that holds all the live data sources.
    private Map<String, DataSource> targetDataSources = new HashMap<String, DataSource>();

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public DbPropertiesUtil getDbPropertiesUtil() {
        return dbPropertiesUtil;
    }

    public void setDbPropertiesUtil(DbPropertiesUtil dbPropertiesUtil) {
        this.dbPropertiesUtil = dbPropertiesUtil;
    }

    /**
     * The setTargetDataSources method has been overridden so that a reference can be kept to allow access to the internal data source map.
     */
    @Override
    public void setTargetDataSources(Map targetDataSources) {
        this.targetDataSources = targetDataSources;
        super.setTargetDataSources(targetDataSources);
    }

    /**
     * Retrieve the key from the session schema bean variable. If this variable is null the default data source will be used if set.
     */
    @Override
    protected Object determineCurrentLookupKey() {
        log.info("Getting schema name.");
        try {
            log.info("  -- Schema name is: " + schema.getName());
            // Try to get the schema name from the session variale. This will fail on start up because there will not be any session threads
            // available.
            return schema.getName();
        } catch(IllegalStateException e) {
            // This exception could get thrown within a unit test though all it means is there is no bean available to be injected.
            // That is fine we can just return null.
            log.info("  -- Schema bean not available, threw IllegalStateException: "
                    + e.getMessage());
        } catch(BeanCreationException e) {
            // Same as the above exception but  this will get thrown on startup.
            log.info("  -- Schema bean not available, threw BeanCreationException: "
                    + e.getMessage());
        }
        return null;
    }

    @Override
    protected DataSource determineTargetDataSource() {
        // If a data source is requested and it does not exist, create it.
        if (determineCurrentLookupKey() != null && !determineCurrentLookupKey().equals("")
                && this.targetDataSources.get((String) determineCurrentLookupKey()) == null) {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(this.dbPropertiesUtil.getDriver());
            dataSource.setUrl(this.dbPropertiesUtil.getUrl());
            dataSource.setUsername(this.dbPropertiesUtil.getSchemaPrefix() + (String)determineCurrentLookupKey());
            dataSource.setPassword(this.dbPropertiesUtil.getDefaultCorePassword());
            this.targetDataSources.put((String) determineCurrentLookupKey(), dataSource);

            this.afterPropertiesSet(); // Reinitialise the routing data source.
        }

        return super.determineTargetDataSource();
    }

    /**
     * Get the data source with the that relates to the given schema.
     */
    public DataSource getDataSource(String schemaName) {
        return this.targetDataSources.get(schemaName);
    }
}

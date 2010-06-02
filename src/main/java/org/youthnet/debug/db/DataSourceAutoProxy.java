package org.youthnet.debug.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.youthnet.debug.util.DbPropertiesUtil;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * User: karl
 * Date: 02-Jun-2010
 */
public class DataSourceAutoProxy extends AbstractRoutingDataSource {

    // Bean to hold all the properties that were used to configure the data sources at start up.
    private DbPropertiesUtil dbPropertiesUtil;

    // Map that holds all the live data sources.
    private Map<String, DataSource> targetDataSources = new HashMap<String, DataSource>();

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
     * Retrieve the key from the global SchemaSelector thread local variable. If this variable is null the default data source will be used if set.
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return SchemaSelector.getSchema();
    }

    @Override
    protected DataSource determineTargetDataSource() {
        // If a data source is requested and it does not exist, create it.
        if (determineCurrentLookupKey() != null && !determineCurrentLookupKey().equals("")
                && this.targetDataSources.get((String) determineCurrentLookupKey()) == null) {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(this.dbPropertiesUtil.getDriver());
            dataSource.setUrl(this.dbPropertiesUtil.getUrl());
            dataSource.setUsername((String) determineCurrentLookupKey());
            dataSource.setPassword(this.dbPropertiesUtil.getPassword());
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

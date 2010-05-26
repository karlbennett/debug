package org.youthnet.debug.dao.jdbc.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.youthnet.debug.dao.jdbc.JdbcDao;
import org.youthnet.debug.util.UuidConverter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * User: Karl
 * Date: 22-May-2010
 */
@Repository("adminJdbcDaoImpl")
public class AdminJdbcDaoImpl implements JdbcDao {

    @Resource(name = "adminJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    // Variable to hold the SQL statement that should be used to select a list of the current schema's table names.
    private String selectTableNamesQuery;

    @Override
    public void executeQuery(String query) {
        jdbcTemplate.execute(query);
    }

    @Override
    public List<Map<String, Object>> queryForRows(String query) {
        return jdbcTemplate.queryForList(query);
    }

    @Override
    public List<String> getTableNames() {
        return jdbcTemplate.queryForList(selectTableNamesQuery, String.class);
    }

    @Override
    public List<Map<String, Object>> getTableRows(String tableName) {
        List<Map<String, Object>> tableRows = queryForRows("SELECT * FROM " + tableName);
        for(Map<String, Object> row : tableRows) {
            for(String key : row.keySet()) {
                if( row.get(key) instanceof byte[]) {
                    row.put(key, UuidConverter.convertByteArrayToUuid((byte[]) row.get(key)));
                }
            }
        }
        return tableRows;
    }

    public String getSelectTableNamesQuery() {
        return selectTableNamesQuery;
    }

    public void setSelectTableNamesQuery(String selectTableNamesQuery) {
        this.selectTableNamesQuery = selectTableNamesQuery;
    }
}

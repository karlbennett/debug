package org.youthnet.debug.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.youthnet.debug.domain.common.UuidType;
import org.youthnet.debug.domain.common.impl.UuidTypeImpl;
import org.youthnet.debug.util.SqlSyntaxUtil;
import org.youthnet.debug.util.UuidConverter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * User: Karl
 * Date: 22-May-2010
 */
public abstract class AbstractJdbcDao implements JdbcDao {

    private static final Log log = LogFactory.getLog(AbstractJdbcDao.class);

    private JdbcTemplate jdbcTemplate;

    @Resource(name = "sqlSyntaxUtil")
    private SqlSyntaxUtil sqlSyntaxUtil;

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
        for (Map<String, Object> row : tableRows) {
            convertByteColumnsToUuidType(row);
        }
        return tableRows;
    }

    @Override
    public Map<String, Object> getRowById(Object id) {

        Map<String, Object> row = null;
        for (String tableName : getTableNames()) {
            try {
                if (id instanceof UuidType) {
                    row = jdbcTemplate.queryForMap("SELECT * FROM " + tableName + " WHERE id = "
                            + sqlSyntaxUtil.getBinTypeStart() + id.toString().replace("-", "") + sqlSyntaxUtil.getBinTypeEnd());
                }

                row = jdbcTemplate.queryForMap("SELECT * FROM " + tableName + " WHERE id = '" + id.toString() + "'");
            } catch (EmptyResultDataAccessException e) {
                log.info("ID: " + id + " not found in " + tableName + ".");
            } catch (BadSqlGrammarException e) {
                if (e.getMessage().contains("ORA-00932")) log.info("ID in " + tableName + " not of type GUID.");
                else if (e.getMessage().contains("ORA-00904"))
                    log.info("Table " + tableName + " does not contain ID column.");
                else throw e;
            }
            if (row != null) {
                row.put("tableName", tableName);
                return convertByteColumnsToUuidType(row);
            }
        }

        return null;
    }

    @Override
    public Map<String, Object> getRowById(Object id, String tableName) {
        if (id instanceof UuidType) {
            return convertByteColumnsToUuidType(jdbcTemplate.queryForMap("SELECT * FROM " + tableName + " WHERE id = "
                    + sqlSyntaxUtil.getBinTypeStart() + id.toString().replace("-", "") + sqlSyntaxUtil.getBinTypeEnd()));
        }

        return convertByteColumnsToUuidType(jdbcTemplate.queryForMap("SELECT * FROM " + tableName + " WHERE id = '"
                + id.toString() + "'"));
    }

    @Override
    public Map<String, Object> getRowByUuidString(String id) {
        return this.getRowById(UuidTypeImpl.fromString(id));
    }

    public Map<String, Object> convertByteColumnsToUuidType(Map<String, Object> row) {
        for (String key : row.keySet()) {
            if (row.get(key) instanceof byte[]) {
                row.put(key, new UuidTypeImpl(UuidConverter.convertByteArrayToUuid((byte[]) row.get(key))));
            }
        }

        return row;
    }

    public Map<String, Object> convertByteColumnsToString(Map<String, Object> row) {
        for (String key : row.keySet()) {
            if (row.get(key) instanceof byte[]) {
                row.put(key, UuidConverter.convertByteArrayToUuid((byte[]) row.get(key)).toString());
            }
        }

        return row;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getSelectTableNamesQuery() {
        return selectTableNamesQuery;
    }

    public void setSelectTableNamesQuery(String selectTableNamesQuery) {
        this.selectTableNamesQuery = selectTableNamesQuery;
    }
}
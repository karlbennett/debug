package org.youthnet.debug.dao.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.youthnet.debug.dao.jdbc.sql.SqlSyntax;
import org.youthnet.debug.domain.common.UuidType;
import org.youthnet.debug.domain.common.impl.UuidTypeImpl;
import org.youthnet.debug.util.conversion.UuidConverter;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: Karl
 * Date: 22-May-2010
 */
public abstract class AbstractJdbcDao implements JdbcDao {

    private static final Log log = LogFactory.getLog(AbstractJdbcDao.class);

    private JdbcTemplate jdbcTemplate;

    @Resource(name = "sqlSyntax")
    private SqlSyntax sqlSyntax;

    @Override
    public void executeQuery(String query) {
        jdbcTemplate.execute(query);
    }

    @Override
    public List<Map<String, Object>> queryForRows(String query) {
        try {
            return jdbcTemplate.queryForList(query);
        } catch (Exception e) {
            if (e.getMessage().contains("ORA-00942")) log.warn("Table not found in query: " + query);
            else log.warn(e.getStackTrace());
        }

        return null;
    }

    @Override
    public List<String> getSchemaNames() {
        return jdbcTemplate.queryForList("SELECT shortname FROM " + sqlSyntax.getAdminSchema() + "collective", String.class);
    }

    @Override
    public List<String> getTableNames() {
        return jdbcTemplate.queryForList(sqlSyntax.getSelectTableNamesQuery(), String.class);
    }

    @Override
    public List<Map<String, Object>> getTableRows(String tableName) {
        List<Map<String, Object>> tableRows = queryForRows("SELECT * FROM " + tableName);
        if (tableRows != null) { // Check for null so foreach doesn't break. 
            for (Map<String, Object> row : tableRows) {
                convertByteColumnsToUuidType(row);
            }
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
                            + sqlSyntax.getBinTypeStart() + id.toString().replace("-", "") + sqlSyntax.getBinTypeEnd());
                } else if (id instanceof Number) {
                    row = jdbcTemplate.queryForMap("SELECT * FROM " + tableName + " WHERE id = " + id.toString());
                } else {
                    row = jdbcTemplate.queryForMap("SELECT * FROM " + tableName + " WHERE id = '" + id.toString() + "'");
                }

            } catch (EmptyResultDataAccessException e) {
                log.info("ID: " + id + " not found in " + tableName + ". Error: " + e.getMessage());
            } catch (BadSqlGrammarException e) {
                log.info("ID column in " + tableName + " not of type " + id.getClass().getSimpleName()
                        + " or it doesn't exist. Error: " + e.getMessage());
            }
            if (row != null) {
                row.put("tableName", tableName);
                row = convertByteColumnsToUuidType(row);

                log.info("ID: " + id + " found in " + tableName + ".");
                log.info(printRow(row));

                return row;
            }
        }

        return null;
    }

    @Override
    public Map<String, Object> getRowById(Object id, String tableName) {
        if (id instanceof UuidType) {
            return convertByteColumnsToUuidType(jdbcTemplate.queryForMap("SELECT * FROM " + tableName + " WHERE id = "
                    + sqlSyntax.getBinTypeStart() + id.toString().replace("-", "") + sqlSyntax.getBinTypeEnd()));
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

    private String printRow(Map<String, Object> row) {
        Set<String> keySet = row.keySet();
        StringBuffer rowStringBuffer = new StringBuffer("ROW:\n");
        for (String columnName : keySet) {
            Object value = row.get(columnName);
            if (value != null) rowStringBuffer.append("    " + columnName + ": " + value.toString() + "\n");   
        }

        return rowStringBuffer.toString();
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
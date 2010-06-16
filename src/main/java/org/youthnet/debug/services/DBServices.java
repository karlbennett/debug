package org.youthnet.debug.services;

import java.util.List;
import java.util.Map;

/**
 * User: karl
 * Date: 16-Jun-2010
 */
public interface DBServices {

    public List<String> getSchemaNames();

    public List<String> getTableNames();

    public List<String> getColumnNames();

    public List<Map<String, Object>> getTableRows(String tableName);

    public String getTableNameForColumn(String column);
}

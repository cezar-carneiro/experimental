package com.oproom.service;

import com.oproom.model.database.DatabaseInfo;
import com.oproom.model.database.IndexInfo;
import com.oproom.model.database.QueryResponse;
import com.oproom.model.database.TableInfo;

import java.util.List;

/**
 * Created by Cezar Carneiro on 27/02/2018.
 */

public interface IDatabaseService {

    List<String> getDatabases();
    DatabaseInfo getDatabaseInfo(String databaseName);
    boolean deleteDatabase(String databaseName);

    TableInfo getTableInfo(String databaseName, String tableName);
    TableInfo getViewInfo(String databaseName, String viewName);
    IndexInfo getIndexInfo(String databaseName, String indexName);

    void dropTable(String databaseName, String tableName);
    void truncateTable(String databaseName, String tableName);

    QueryResponse execQuery(String databaseName, String sql);

}

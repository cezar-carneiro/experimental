package com.oproom.service;

import com.oproom.model.database.DatabaseInfo;
import com.oproom.model.database.SQLResponse;

import java.util.List;

/**
 * Created by Cezar Carneiro on 27/02/2018.
 */

public interface IDatabaseService {

    List<String> getDatabases();
    DatabaseInfo getDatabaseInfo(String databaseName);
    boolean deleteDatabase(String databaseName);

    SQLResponse dropTable(String databaseName, String tableName);
    SQLResponse dropIndex(String databaseName, String indexName);
    SQLResponse dropView(String databaseName, String viewName);

    void truncateTable(String databaseName, String tableName);

    SQLResponse execSQL(String databaseName, String sql);

}

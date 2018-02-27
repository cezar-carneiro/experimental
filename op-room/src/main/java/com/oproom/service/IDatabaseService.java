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
    DatabaseInfo getDatabaseInfo();
    boolean deleteDatabase();

    TableInfo getTableInfo(String name);
    TableInfo getViewInfo(String name);
    IndexInfo getIndexInfo(String name);

    void dropTable(String name);
    void truncateTable(String name);
    void createTable();

    QueryResponse execQuery();

}

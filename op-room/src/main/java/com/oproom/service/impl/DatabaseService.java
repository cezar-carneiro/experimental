package com.oproom.service.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.oproom.model.database.DatabaseInfo;
import com.oproom.model.database.IndexInfo;
import com.oproom.model.database.QueryResponse;
import com.oproom.model.database.TableInfo;
import com.oproom.service.BaseService;
import com.oproom.service.IDatabaseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cezar Carneiro on 6/2/2018.
 */

public class DatabaseService extends BaseService implements IDatabaseService {

    private SQLiteDatabase database;

    public DatabaseService(Context context, SQLiteDatabase database) {
        super(context);
        this.database = database;
    }

    private SQLiteDatabase openDatabase(String databasePath){
        return SQLiteDatabase.openDatabase(databasePath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    private String getDatabasePath(String databaseName) {
        return mContext.getDatabasePath(databaseName).getPath();
    }

    @Override
    public List<String> getDatabases() {
        return Arrays.asList(mContext.databaseList());
    }

    @Override
    public DatabaseInfo getDatabaseInfo(String databaseName) {
        String databasePath = getDatabasePath(databaseName);
        SQLiteDatabase db = openDatabase(databasePath);

        DatabaseInfo di = new DatabaseInfo();
        di.setDatabaseName(databaseName);
        di.setDatabasePath(databasePath);
        di.setMaximumSize(db.getMaximumSize());
        di.setPageSize(db.getPageSize());
        di.setVersion(db.getVersion());

        try {
            Cursor cursor = database.rawQuery("SELECT name, type FROM sqlite_master WHERE type IN (?, ?, ?)",
                    new String[] { "table", "view", "index" });
            try {
                List<String> tables = new ArrayList<>();
                List<String> views = new ArrayList<>();
                List<String> indices = new ArrayList<>();
                while (cursor.moveToNext()) {
                    switch (cursor.getString(1)){
                        case "table":
                            tables.add(cursor.getString(0));
                            break;
                        case "view":
                            views.add(cursor.getString(0));
                            break;
                        case "index":
                            indices.add(cursor.getString(0));
                            break;
                    }
                }
                di.setTables(tables);
                di.setViews(views);
                di.setIndices(indices);
            } finally {
                cursor.close();
            }
        } finally {
            database.close();
        }

        return di;
    }

    public boolean deleteDatabase(String name) {
        return mContext.deleteDatabase(name);
    }

    @Override
    public TableInfo getTableInfo(String databaseName, String tableName) {
        return null;
    }

    @Override
    public TableInfo getViewInfo(String databaseName, String viewName) {
        return null;
    }

    @Override
    public IndexInfo getIndexInfo(String databaseName, String indexName) {
        return null;
    }

    @Override
    public void dropTable(String databaseName, String tableName) {

    }

    @Override
    public void truncateTable(String databaseName, String tableName) {

    }

    @Override
    public QueryResponse execQuery(String databaseName, String sql) {
        return null;
    }

}

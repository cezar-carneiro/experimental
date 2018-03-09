package com.oproom.service.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.oproom.model.database.ColumnType;
import com.oproom.model.database.DatabaseInfo;
import com.oproom.model.database.SQLResponse;
import com.oproom.model.database.TableInfo;
import com.oproom.model.database.TableType;
import com.oproom.service.BaseService;
import com.oproom.service.IDatabaseService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cezar Carneiro on 6/2/2018.
 */

public class DatabaseService extends BaseService implements IDatabaseService {

    public DatabaseService(Context context) {
        super(context);
    }

    private SQLiteDatabase openDatabase(String databasePath) {
        return SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
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
            Cursor cursor = db.rawQuery("SELECT name, type, sql FROM sqlite_master WHERE type IN (?, ?, ?)",
                    new String[]{"table", "view", "index"});
            try {
                List<TableInfo> tables = new ArrayList<>();
                while (cursor.moveToNext()) {
                    TableInfo ti = new TableInfo();
                    ti.setName(cursor.getString(0));
                    ti.setType(TableType.valueOf(cursor.getString(1).toUpperCase()));
                    ti.setSql(cursor.getString(2));
                    tables.add(ti);
                }
                di.setTables(tables);
            } finally {
                cursor.close();
            }
        } finally {
            db.close();
        }
        return di;
    }

    @Override
    public boolean deleteDatabase(String name) {
        return mContext.deleteDatabase(name);
    }

    @Override
    public SQLResponse dropTable(String databaseName, String tableName) {
        return drop(databaseName, TableType.TABLE, tableName);
    }

    @Override
    public SQLResponse dropIndex(String databaseName, String indexName) {
        return drop(databaseName, TableType.INDEX, indexName);
    }

    @Override
    public SQLResponse dropView(String databaseName, String viewName) {
        return drop(databaseName, TableType.VIEW, viewName);
    }

    private SQLResponse drop(String databaseName, TableType type, String name){
        String sql = String.format("DROP %s %s", type.toString(), name);
        SQLResponse response = execSQL(databaseName, sql);
        return response;
    }

    @Override
    public void truncateTable(String databaseName, String tableName) {
        String databasePath = getDatabasePath(databaseName);
        SQLiteDatabase db = openDatabase(databasePath);

        try {
            db.delete(tableName, null, null);
        } catch (Exception e){

        } finally {
            db.close();
        }
    }

    @Override
    public SQLResponse execSQL(String databaseName, String sql) {
        long before = System.currentTimeMillis();
        SQLResponse response = new SQLResponse();
        String databasePath = getDatabasePath(databaseName);
        SQLiteDatabase db = openDatabase(databasePath);
        try {
            Cursor cursor = db.rawQuery(sql, null);
            response.setCount(cursor.getCount());
            response.setColumns(cursor.getColumnNames());
            try {
                response.setColumnTypes(getColumnTypes(cursor));
                response.setRows(getRows(cursor));
            } catch (Exception e){
                response.setSuccessful(false);
            } finally {
                cursor.close();
            }
        } catch (Exception e){
            response.setSuccessful(false);
        } finally {
            db.close();
        }

        response.setSuccessful(true);
        long after = System.currentTimeMillis();
        response.setDuration(after - before);
        return response;
    }

    private ColumnType[] getColumnTypes(Cursor cursor){
        ColumnType[] types = new ColumnType[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            switch (cursor.getType(i)) {
                case Cursor.FIELD_TYPE_INTEGER:
                    types[i] = ColumnType.INTEGER;
                    break;
                case Cursor.FIELD_TYPE_FLOAT:
                    types[i] = ColumnType.FLOAT;
                    break;
                case Cursor.FIELD_TYPE_BLOB:
                    types[i] = ColumnType.BLOB;
                    break;
                case Cursor.FIELD_TYPE_STRING:
                    types[i] = ColumnType.STRING;
                    break;
                default:
                    types[i] = ColumnType.UNKNOWN;
                    break;
            }
        }
        return types;
    }

    private String[][] getRows(Cursor cursor){
        int columnCount = cursor.getColumnCount();
        String[][] rows = new String[cursor.getCount()][];
        for(int i = 0; cursor.moveToNext(); i++) {
            String[] row = new String[columnCount];
            for(int j = 0; j < columnCount; j++){
                switch (cursor.getType(j)) {
                    case Cursor.FIELD_TYPE_NULL:
                        row[j] = "NULL";
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        row[j] = String.valueOf(cursor.getLong(j));
                        break;
                    case Cursor.FIELD_TYPE_FLOAT:
                        row[j] = String.valueOf(cursor.getDouble(j));
                        break;
                    case Cursor.FIELD_TYPE_BLOB:
                        row[j] = "[BLOB]";
                        break;
                    case Cursor.FIELD_TYPE_STRING:
                    default:
                        row[j] = cursor.getString(j);
                        break;
                }
            }
            rows[i] = row;
        }
        return rows;
    }

}

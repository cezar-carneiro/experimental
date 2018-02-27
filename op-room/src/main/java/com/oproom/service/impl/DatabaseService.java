package com.oproom.service.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.oproom.model.database.DatabaseInfo;
import com.oproom.service.BaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cezar Carneiro on 6/2/2018.
 */

public class DatabaseService extends BaseService {

    private SQLiteDatabase database;

    public DatabaseService(Context context, SQLiteDatabase database) {
        super(context);
        this.database = database;
    }

    public List<DatabaseInfo> getDatabasesInfo() {
        List<DatabaseInfo> list = new ArrayList();
        for (String databaseName : mContext.databaseList()) {
            DatabaseInfo info = new DatabaseInfo();
            mContext.getDatabasePath(databaseName);
            list.add(info);
        }
        return list;
    }

    public boolean deleteDatabase(String name) {
        return mContext.deleteDatabase(name);
    }

    public List getTableInfo() {
        return null;
    }

}

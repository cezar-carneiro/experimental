package com.oproom.model.database;

/**
 * Created by Cezar Carneiro on 27/02/2018.
 */

public class TableInfo {

    private String name;
    private TableType type;
    private String sql;

    public TableInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TableType getType() {
        return type;
    }

    public void setType(TableType type) {
        this.type = type;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}

package com.oproom;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Cezar Carneiro on 10/04/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    static private final String DB_NAME = "test_db";
    static private final int DB_VERSION = 1;

    static private final String CREATE_SCRIPT = "CREATE TABLE Person (id INTEGER NOT NULL, name TEXT NOT NULL, PRIMARY KEY(id));" +
            "CREATE TABLE Address (id INTEGER NOT NULL, address TEXT NOT NULL, city TEXT NOT NULL, country TEXT NOT NULL, postcode TEXT NULL, person_id INTEGER NOT NULL, FOREIGN KEY(person_id) REFERENCES Person(id), PRIMARY KEY(id));";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SCRIPT);
        insertTestData();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void insertTestData() {
        SQLiteDatabase db = this.getWritableDatabase();
        insertPerson(db, 1,"Clark Kent");
        insertPerson(db, 2,"Barry Allen");
        insertPerson(db, 3,"Diana Prince");

        insertAddress(db, 1, "Kent Farm", "Smallville", "United States", "66101", 1);
        insertAddress(db, 2, "Streak Lane, 1939", "Central City", "United States", "10001", 2);
        insertAddress(db, 3, "Lasso Of Truth Avenue, 1941 ", "Themyscira", "Greece", null, 3);
        db.close();
    }

    private void insertPerson(SQLiteDatabase db, int id, String name) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);

        db.insert("Person", null, values);
    }
    private void insertAddress(SQLiteDatabase db, int id, String address, String city, String country, String postcode, int personId) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("address", address);
        values.put("city", city);
        values.put("country", country);
        values.put("postcode", postcode);
        values.put("person_id", personId);

        db.insert("Address", null, values);
    }

}

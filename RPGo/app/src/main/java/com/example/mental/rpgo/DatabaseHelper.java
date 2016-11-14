package com.example.mental.rpgo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mental on 8/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "appDb.db";
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_PASSWORD = "password";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "create table user " + "(id integer primary key, name VARCHAR, password VARCHAR)");
        db.execSQL("INSERT INTO user VALUES(1,'admin','admin');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }

    public Cursor getID(String name, String pwd) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select id from user where name='"+name+"' AND password='"+pwd+"'", null );
        return res;
    }


    public boolean insert_user(String name, String pwd) {

        boolean insertSuccessful = false;

        ContentValues values = new ContentValues();


        values.put(USER_COLUMN_NAME, name);
        values.put(USER_COLUMN_PASSWORD, pwd);

        SQLiteDatabase db = this.getWritableDatabase();

        insertSuccessful = db.insert(USER_TABLE_NAME, null, values) > 0;
        // insertSuccessful=false;
        db.close();

        return insertSuccessful;
    }
}

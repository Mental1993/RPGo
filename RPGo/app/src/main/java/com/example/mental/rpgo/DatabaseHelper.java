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
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_NOGRIF = "nogrif";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 5);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( "CREATE TABLE user " + "(" + USER_COLUMN_ID + " INTEGER PRIMARY KEY, " + USER_COLUMN_NAME + " VARCHAR, " + USER_COLUMN_PASSWORD + " VARCHAR, " + USER_COLUMN_EMAIL + " VARCHAR, " + USER_COLUMN_NOGRIF + " misoINTEGER)");
        db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES(1, 'admin', 'admin', 'admin@admin.com', 1);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
            onCreate(db);
        }

    public Cursor getID(String name, String pwd, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT " + USER_COLUMN_ID + "," + USER_COLUMN_NOGRIF + " FROM " + USER_TABLE_NAME + " WHERE (" + USER_COLUMN_NAME + "='"+name+"' AND " + USER_COLUMN_PASSWORD + "='"+pwd+"') OR (" +
                " " + USER_COLUMN_EMAIL + "='"+email+"' AND " + USER_COLUMN_PASSWORD + "='"+pwd+"')", null );
        return res;
    }
    public boolean insert_user(String name, String pwd, String newemail) {


        boolean insertSuccessful = false;

        ContentValues values = new ContentValues();

        values.put(USER_COLUMN_NAME, name);
        values.put(USER_COLUMN_PASSWORD, pwd);
        values.put(USER_COLUMN_EMAIL, newemail);
        values.put(USER_COLUMN_NOGRIF, 1);

        SQLiteDatabase db = this.getWritableDatabase();

        insertSuccessful = db.insert(USER_TABLE_NAME, null, values) > 0;

        db.close();

        return insertSuccessful;
    }

    public boolean update_user(int nr,int id){
        boolean updateSuccessful;
        ContentValues values = new ContentValues();

        values.put(USER_COLUMN_NOGRIF,nr);
        SQLiteDatabase db = this.getWritableDatabase();

        updateSuccessful = db.update(USER_TABLE_NAME,values, " id = " + id, null) > 0;
        return updateSuccessful;
    }

    public Cursor user_exists(String usrname, String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + USER_COLUMN_PASSWORD + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_NAME + "='"+usrname+"' AND " + USER_COLUMN_EMAIL + "='"+email+"'", null);

        return cursor;
    }

    public boolean check_duplicate(String fieldName, String value) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + USER_COLUMN_ID + " FROM " + USER_TABLE_NAME + " WHERE " + fieldName + "='"+value+"'", null);
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }


}

package com.example.mental.rpgo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mental on 8/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "appDb.db";
    public static final String USER_TABLE_NAME = "user";
    public static final String ACHIVEMENT_TABLE_NAME = "achivement";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_NOGRIF = "nogrif";
    public static final String USER_COLUMN_TIMESTAMP = "timestamp";
    public static final String USER_COLUMN_KEYS = "keys_count";
    public static final String USER_COLUMN_KEYS_TIMESTAMP = "keys_timestamp";
    public static final String ACHIVEMENT_COLUMN_ID = "ach_id";
    public static final String ACHIVEMENT_COLUMN_NAME = "ach_name";
    public static final String ACHIVEMENT_COLUMN_DESC = "ach_desc";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 11);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create user table and add admin account
        db.execSQL( "CREATE TABLE " + USER_TABLE_NAME + " " + "(" + USER_COLUMN_ID + " INTEGER PRIMARY KEY, " + USER_COLUMN_NAME + " VARCHAR, " + USER_COLUMN_PASSWORD + " VARCHAR, " + USER_COLUMN_EMAIL + " VARCHAR, " + USER_COLUMN_NOGRIF + " INTEGER, " + USER_COLUMN_KEYS + " INTEGER, "+ USER_COLUMN_KEYS_TIMESTAMP + " REAL, " + USER_COLUMN_TIMESTAMP + " REAL)");
        db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES(1, 'admin', 'admin', 'admin@admin.com', 1, 0, 0, 0);");

        //create achivement table
        db.execSQL("CREATE TABLE " + ACHIVEMENT_TABLE_NAME + " " + "(" + ACHIVEMENT_COLUMN_ID + " INTEGER PRIMARY KEY, " + ACHIVEMENT_COLUMN_NAME + " VARCHAR, " + ACHIVEMENT_COLUMN_DESC + " VARCHAR)");
        db.execSQL("INSERT INTO " + ACHIVEMENT_TABLE_NAME + " VALUES(1, 'Time passed', 'Achivement completed when 1 week is passed since the user sign up date.');");
        db.execSQL("INSERT INTO " + ACHIVEMENT_TABLE_NAME + " VALUES(2, 'Locations visited', 'Achivement completed when the user visits 3 locations.');");
        db.execSQL("INSERT INTO " + ACHIVEMENT_TABLE_NAME + " VALUES(3, 'Riddles solved', 'Achivement completed when the user completes 3 riddles.');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + ACHIVEMENT_TABLE_NAME);
            onCreate(db);
    }

    public List<AchivementObject> getAll() {
        List<AchivementObject> achivements = new ArrayList<AchivementObject>();
        AchivementObject ach = null;
        Cursor c = null;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            c = db.rawQuery("SELECT * FROM " + ACHIVEMENT_TABLE_NAME, null);
            if(c.moveToFirst()) {
                do {
                    ach = new AchivementObject();
                    ach.setId(c.getInt(c.getColumnIndex(ACHIVEMENT_COLUMN_ID)));
                    ach.setName(c.getString(c.getColumnIndex(ACHIVEMENT_COLUMN_NAME)));
                    ach.setDesc(c.getString(c.getColumnIndex(ACHIVEMENT_COLUMN_DESC)));
                    achivements.add(ach);
                }while(c.moveToNext());
            }
            return achivements;
        }finally {
            if(c != null) {
                c.close();
            }
        }
    }

    public Cursor getID(String name, String pwd, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT " + USER_COLUMN_ID + "," + USER_COLUMN_NOGRIF + " FROM " + USER_TABLE_NAME + " WHERE (" + USER_COLUMN_NAME + "='"+name+"' AND " + USER_COLUMN_PASSWORD + "='"+pwd+"') OR (" +
                " " + USER_COLUMN_EMAIL + "='"+email+"' AND " + USER_COLUMN_PASSWORD + "='"+pwd+"')", null );
        return res;
    }

    public int getKeys(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int keys_count;
        Cursor res = db.rawQuery("SELECT " + USER_COLUMN_KEYS + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_ID + "='"+id+"'", null);
        if(res.moveToFirst() && res != null) {
            keys_count = res.getInt(res.getColumnIndex(USER_COLUMN_KEYS));
            return keys_count;
        }else {
            return 0;
        }
    }

    public double getTimestamp(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        double timestamp = 10;
        Cursor res =  db.rawQuery( "SELECT " + USER_COLUMN_TIMESTAMP + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_ID + "='"+id+"'", null );
        if(res != null && res.moveToFirst()) {
            timestamp = Double.valueOf(res.getDouble(res.getColumnIndex(USER_COLUMN_TIMESTAMP)));
            return timestamp;
        }
        return timestamp;
    }

    public double getKeysTimestamp(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        double keys_timestamp = 10;
        Cursor res =  db.rawQuery( "SELECT " + USER_COLUMN_KEYS_TIMESTAMP + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_ID + "='"+id+"'", null );
        if(res != null && res.moveToFirst()) {
            keys_timestamp = Double.valueOf(res.getDouble(res.getColumnIndex(USER_COLUMN_KEYS_TIMESTAMP)));
            return keys_timestamp;
        }
        return keys_timestamp;
    }

    public boolean setKeysTimestamp(double timeRemaining, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_KEYS_TIMESTAMP, timeRemaining);
        String where = USER_COLUMN_ID + "=" + id;
        try {
            db.update(USER_TABLE_NAME, values, where, null);
            return true;
        }catch (Exception e) { return false; }
    }


    public boolean insert_user(String name, String pwd, String newemail, int keys_count, double keys_timestamp, double timestamp) {
        boolean insertSuccessful = false;
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_NAME, name);
        values.put(USER_COLUMN_PASSWORD, pwd);
        values.put(USER_COLUMN_EMAIL, newemail);
        values.put(USER_COLUMN_KEYS, keys_count);
        values.put(USER_COLUMN_KEYS_TIMESTAMP, keys_timestamp);
        values.put(USER_COLUMN_NOGRIF, 1);
        values.put(USER_COLUMN_TIMESTAMP, timestamp);

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

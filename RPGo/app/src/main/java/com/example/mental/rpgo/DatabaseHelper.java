package com.example.mental.rpgo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Location;
import android.util.Log;

import java.lang.reflect.Array;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mental on 8/11/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "appDb.db";
    //table names
    public static final String USER_TABLE_NAME = "user";
    public static final String ACHIVEMENT_TABLE_NAME = "achivement";
    public static final String KEYS_LOCATION_TABLE_NAME = "keys_location";
    //Column names
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_PASSWORD = "password";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_NOGRIF = "nogrif";
    public static final String USER_COLUMN_TIMESTAMP = "timestamp";
    public static final String USER_COLUMN_KEYS = "keys_count";
    public static final String USER_COLUMN_KEYS_TIMESTAMP = "keys_timestamp";
    public static final String USER_COLUMN_LOC_VISITED = "loc_visited";

    public static final String ACHIVEMENT_COLUMN_ID = "ach_id";
    public static final String ACHIVEMENT_COLUMN_NAME = "ach_name";
    public static final String ACHIVEMENT_COLUMN_DESC = "ach_desc";

    public static final String KEYS_LOCATION_COLUMN_ID = "keys_loc_id";
    public static final String KEYS_LOCATION_COLUMN_NAME = "keys_loc_name";
    public static final String KEYS_LOCATION_COLUMN_LAT = "keys_loc_lat";
    public static final String KEYS_LOCATION_COLUMN_LNG = "keys_loc_lng";
    public static final String USER_COLUMN_IMAGE ="image" ;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 19);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create user table and add admin account
        db.execSQL( "CREATE TABLE " + USER_TABLE_NAME + " " + "(" + USER_COLUMN_ID + " INTEGER PRIMARY KEY, " + USER_COLUMN_NAME + " VARCHAR, " + USER_COLUMN_PASSWORD + " VARCHAR, " + USER_COLUMN_EMAIL + " VARCHAR, " + USER_COLUMN_NOGRIF + " INTEGER, " + USER_COLUMN_KEYS + " INTEGER, " + USER_COLUMN_LOC_VISITED + " INTEGER, " + USER_COLUMN_KEYS_TIMESTAMP + " REAL, " + USER_COLUMN_TIMESTAMP + " REAL)");
        db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES(1, 'admin', 'admin', 'admin@admin.com', 1, 0, 0, 0, 0);");

        //create achivement table
        db.execSQL("CREATE TABLE " + ACHIVEMENT_TABLE_NAME + " " + "(" + ACHIVEMENT_COLUMN_ID + " INTEGER PRIMARY KEY, " + ACHIVEMENT_COLUMN_NAME + " VARCHAR, " + ACHIVEMENT_COLUMN_DESC + " VARCHAR)");
        db.execSQL("INSERT INTO " + ACHIVEMENT_TABLE_NAME + " VALUES(1, 'Time passed', 'Achivement completed when 1 week is passed since the user sign up date.');");
        db.execSQL("INSERT INTO " + ACHIVEMENT_TABLE_NAME + " VALUES(2, 'Locations visited', 'Achivement completed when the user visits 3 locations.');");
        db.execSQL("INSERT INTO " + ACHIVEMENT_TABLE_NAME + " VALUES(3, 'Riddles solved', 'Achivement completed when the user completes 3 riddles.');");
        db.execSQL("INSERT INTO " + ACHIVEMENT_TABLE_NAME + " VALUES(4, 'Keys gathered', 'Achivement completed when the user collects 3 keys.');");

        //create keys_location table
        db.execSQL("CREATE TABLE " + KEYS_LOCATION_TABLE_NAME + " " + "(" + KEYS_LOCATION_COLUMN_ID + " INTEGER PRIMARY KEY, " + KEYS_LOCATION_COLUMN_NAME + " VARCHAR, " + KEYS_LOCATION_COLUMN_LAT + " REAL, " + KEYS_LOCATION_COLUMN_LNG + " REAL)");
        db.execSQL("INSERT INTO " + KEYS_LOCATION_TABLE_NAME + " VALUES(1, 'location key 1', 41.34625656109494, 23.876423419982885)");
        db.execSQL("INSERT INTO " + KEYS_LOCATION_TABLE_NAME + " VALUES(2, 'location key 2', 40.956159506548275, 23.8593573633056)");
        db.execSQL("INSERT INTO " + KEYS_LOCATION_TABLE_NAME + " VALUES(3, 'location key 3', 41.38586966768456, 23.414324179283202)");
        db.execSQL("INSERT INTO " + KEYS_LOCATION_TABLE_NAME + " VALUES(4, 'location key 4', 40.99176955914814, 23.727318147985063)");
        db.execSQL("INSERT INTO " + KEYS_LOCATION_TABLE_NAME + " VALUES(5, 'location key 5', 41.305480898681814, 23.24211240620576)");
        db.execSQL("INSERT INTO " + KEYS_LOCATION_TABLE_NAME + " VALUES(6, 'location key 6', 40.819557786070696, 23.7801450408843)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + ACHIVEMENT_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + KEYS_LOCATION_TABLE_NAME);
            onCreate(db);
    }

    public void fillKeysLoc(List<Location> listLoc) {
        SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT " + KEYS_LOCATION_COLUMN_LAT + "," + KEYS_LOCATION_COLUMN_LNG + " FROM " + KEYS_LOCATION_TABLE_NAME, null);
            while(c.moveToNext()) {
                Location loc = new Location("");
                loc.setLatitude(c.getDouble(c.getColumnIndex(KEYS_LOCATION_COLUMN_LAT)));
                loc.setLongitude(c.getDouble(c.getColumnIndex(KEYS_LOCATION_COLUMN_LNG)));
                listLoc.add(loc);
            }
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

    public boolean deleteKeys_loc(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("DELETE FROM " + KEYS_LOCATION_TABLE_NAME + " WHERE " + KEYS_LOCATION_COLUMN_ID + "='"+id+"'", null);
        if( res != null) {
            return true;
        }else {
            return false;
        }
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

    public void setKeys(String id, int value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_KEYS, value);
        String where = USER_COLUMN_ID + "=" + id;
        try {
            db.update(USER_TABLE_NAME, values, where, null);
        }catch (Exception e) {e.printStackTrace();}
    }

    public int getLoc_visited(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int loc_visited;
        Cursor res = db.rawQuery("SELECT " + USER_COLUMN_LOC_VISITED + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_ID + "='"+id+"'", null);
        if(res.moveToFirst() && res != null) {
            loc_visited = res.getInt(res.getColumnIndex(USER_COLUMN_LOC_VISITED));
            return loc_visited;
        }else {
            return 0;
        }
    }

    public void setLoc_visited(String id, int value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_LOC_VISITED, value);
        String where = USER_COLUMN_ID + "=" + id;
        try {
            db.update(USER_TABLE_NAME, values, where, null);
        }catch (Exception e) {e.printStackTrace();}
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


    public boolean insert_user(String name, String pwd, String newemail, double keys_timestamp) {
        boolean insertSuccessful = false;
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_NAME, name);
        values.put(USER_COLUMN_PASSWORD, pwd);
        values.put(USER_COLUMN_EMAIL, newemail);
        //values.put(USER_COLUMN_KEYS, keys_count);
       // values.put(USER_COLUMN_LOC_VISITED, loc_visited);
        values.put(USER_COLUMN_KEYS_TIMESTAMP, keys_timestamp);
        values.put(USER_COLUMN_NOGRIF, 1);
       // values.put(USER_COLUMN_TIMESTAMP, timestamp);

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

    public Cursor getImage(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT " + USER_COLUMN_IMAGE+ " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_ID+ "='"+id+"'",null);

        return res;
    }

}

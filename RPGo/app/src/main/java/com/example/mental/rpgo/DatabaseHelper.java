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
 * This class provides all the required functions and information about the database. Connection, setting and getting data from it
 *
 * @author Mental
 * @version 1.1
 *
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


    /**
     *
     * Constructor for the {@link DatabaseHelper} class
     * @param context Context: Shows the current state of the application/object
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, 21);
    }


    /**
     * Called whenever a {@link DatabaseHelper} object is instantiated.
     * Creates the users, achivements and keys_location tables, and fills them with the required data
     * @param db DatabaseHelper: A {@link DatabaseHelper} object
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create user table and add admin account
        db.execSQL( "CREATE TABLE " + USER_TABLE_NAME + " " + "(" + USER_COLUMN_ID + " INTEGER PRIMARY KEY, " + USER_COLUMN_NAME + " VARCHAR, " + USER_COLUMN_PASSWORD + " VARCHAR, " + USER_COLUMN_EMAIL + " VARCHAR, " + USER_COLUMN_NOGRIF + " INTEGER, " + USER_COLUMN_KEYS + " INTEGER, " + USER_COLUMN_LOC_VISITED + " INTEGER, " + USER_COLUMN_KEYS_TIMESTAMP + " REAL, " + USER_COLUMN_TIMESTAMP + " REAL, " + USER_COLUMN_IMAGE +" INTEGER)");
        db.execSQL("INSERT INTO " + USER_TABLE_NAME + " VALUES(1, 'admin', 'admin', 'admin@admin.com', 1, 0, 0, 0, 0,0);");

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

    /**
     * Drops all the existing tables ad re creates them
     *
     * @param db DatabaseHelper: A {@link DatabaseHelper} object
     * @param oldVersion int: The current version number of the database
     * @param newVersion int: The desired version number of the database(Should be higger than the {@param oldVersion}
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + ACHIVEMENT_TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + KEYS_LOCATION_TABLE_NAME);
            onCreate(db);
    }

    /**
     * Fills a given {@link List} array with the locations of the keys stored in the database
     * @param listLoc List<Location>: A {@link List} array that holds all the locations of the keys
     */
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

    /**
     * Gets an array filled with {@link AchivementObject} information
     * @return The {@link List<AchivementObject>} array which stores all of the achivement information like id, name and descryption
     */
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

    /**
     * Runs a query to the users table, trying to find a corresponding id for a given name, password and email
     *
     * @param name String: The given name to match the one at the database
     * @param pwd String: The given password to match the one at the database
     * @param email String: The given email to match the one at the database
     * @return The {@link Cursor} instance of the query run inside the function
     */
    public Cursor getID(String name, String pwd, String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT " + USER_COLUMN_ID + "," + USER_COLUMN_NOGRIF + " FROM " + USER_TABLE_NAME + " WHERE (" + USER_COLUMN_NAME + "='"+name+"' AND " + USER_COLUMN_PASSWORD + "='"+pwd+"') OR (" +
                " " + USER_COLUMN_EMAIL + "='"+email+"' AND " + USER_COLUMN_PASSWORD + "='"+pwd+"')", null );
        return res;
    }

    /**
     * Deletes the column with the given id from keys_location table. If there is no match for the given id, returns false.
     *
     * @param id int: The {@link Integer} instance of the given id
     * @return The {@link Boolean} instance of the query result. True if a result was found. Else false.
     */
    public boolean deleteKeys_loc(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("DELETE FROM " + KEYS_LOCATION_TABLE_NAME + " WHERE " + KEYS_LOCATION_COLUMN_ID + "='"+id+"'", null);
        if( res != null) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Given a {@link String} instance of id, the functions tries to find the number of keys collected by the user up to this moment.
     *
     * @param id String: The {@link String} instance of the given id
     * @return The {@link Integer} instance of the number of the keys found from the user up to this moment.
     */
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

    /**
     * Given a number of keys and an id, the function tries to update the number of the keys collected bu the user. Throws an Exception if the query fails.
     *
     * @param id String: The {@link String} instance of the given id
     * @param value int: The {@link Integer} instance of the given number of keys
     * @throws Exception
     * @see Exception
     */
    public void setKeys(String id, int value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_KEYS, value);
        String where = USER_COLUMN_ID + "=" + id;
        try {
            db.update(USER_TABLE_NAME, values, where, null);
        }catch (Exception e) {e.printStackTrace();}
    }

    /**
     * Given an id, the functions tries to find the total locations visited by the user.
     *
     * @param id String: The {@link String} instance of the given id
     * @return The {@link Integer} instance of the total locations visited by the user.0 if the query fails
     */
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

    /**
     * Ties to update the number of locations visited bu the user.
     *
     * @param id String: The {@link String} instance of the given id
     * @param value int: The {@link Integer} instance of the number of locations visited by the user. Throws an Excetion if the query fails.
     * @throws Exception
     * @see Exception
     *
     */
    public void setLoc_visited(String id, int value) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_LOC_VISITED, value);
        String where = USER_COLUMN_ID + "=" + id;
        try {
            db.update(USER_TABLE_NAME, values, where, null);
        }catch (Exception e) {e.printStackTrace();}
    }

    /**
     * Given an id, this function tries to find the time passes since the user's sign up
     *
     * @param id String: The {@link String} instance of the given id
     * @return The {@link Double} instance of seconds passed since the user's sign up. 0 if the query fails.
     */
    public double getTimestamp(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        double timestamp = 0;
        Cursor res =  db.rawQuery( "SELECT " + USER_COLUMN_TIMESTAMP + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_ID + "='"+id+"'", null );
        if(res != null && res.moveToFirst()) {
            timestamp = Double.valueOf(res.getDouble(res.getColumnIndex(USER_COLUMN_TIMESTAMP)));
            return timestamp;
        }
        return timestamp;
    }

    /**
     * Given an id, this functions tries to find the time since the last update of the keys location.
     *
     * @param id String: The {@link String} instance of the given id
     * @return The {@link Double} instance of time passed since the keys locations where last updated. Returrnes 0 if the query fails.
     */
    public double getKeysTimestamp(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        double keys_timestamp = 0;
        Cursor res =  db.rawQuery( "SELECT " + USER_COLUMN_KEYS_TIMESTAMP + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_ID + "='"+id+"'", null );
        if(res != null && res.moveToFirst()) {
            keys_timestamp = Double.valueOf(res.getDouble(res.getColumnIndex(USER_COLUMN_KEYS_TIMESTAMP)));
            return keys_timestamp;
        }
        return keys_timestamp;
    }

    /**
     * Given an id and a value, this function updates the time remaining for the keys location until they are re-arranged.
     * @param timeRemaining double: The {@link Double} instance of the new time remaining of the key location
     * @param id String: The {@link String} instance of the given id
     * @return The {@link Boolean} instance of the result of the query run. If the query fails, returns false and an Exception
     * @throws Exception
     * @see Exception
     */
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

    /**
     * Given all the information required, this functions tries to insert a new user into the database
     *
     * @param name String: The {@link String} instance of the new user's name
     * @param pwd String: The {@link String} instance of the new user's password
     * @param newemail String: The {@link String} instance of the new user's email
     * @param keys_timestamp double: The {@link Double} instance of the remaining time until the keys locations re-arrange
     * @return The {@link Boolean} result of the query. False if the query failed.
     */
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
      //  values.put(USER_COLUMN_TIMESTAMP, timestamp);

        SQLiteDatabase db = this.getWritableDatabase();

        insertSuccessful = db.insert(USER_TABLE_NAME, null, values) > 0;

        db.close();

        return insertSuccessful;
    }

    /**
     * Updates the user's current riddle state
     * @param nr int: The {@link Integer} instance of the next riddle to be solved by the user
     * @param id int: The {@link Integer} instance of the given id
     * @return The {@link Boolean} instance of the result of the query. Returns false, if the quert fails.
     */
    public boolean update_user(int nr,int id){
        boolean updateSuccessful;
        ContentValues values = new ContentValues();

        values.put(USER_COLUMN_NOGRIF,nr);
        SQLiteDatabase db = this.getWritableDatabase();

        updateSuccessful = db.update(USER_TABLE_NAME,values, " id = " + id, null) > 0;
        return updateSuccessful;
    }

    /**
     * Given a username and an email, this functions tries to match them with an existing id.
     *
     * @param usrname String: The {@link String} instance of the given username
     * @param email String: The {@link String} instance of the given email
     * @return The {@link Cursor} instance of the query run
     */
    public Cursor user_exists(String usrname, String email) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + USER_COLUMN_PASSWORD + " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_NAME + "='"+usrname+"' AND " + USER_COLUMN_EMAIL + "='"+email+"'", null);

        return cursor;
    }

    /**
     * Given a field name and a value, this function checks if there is a duplicate insertion inside the users table
     * @param fieldName String: The {@link String} instance of given field name
     * @param value String: The {@link String} instance of the given value
     * @return The {@link Boolean} instance of the result of the query. Returns false if there is NOT a duplicate.
     */
    public boolean check_duplicate(String fieldName, String value) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + USER_COLUMN_ID + " FROM " + USER_TABLE_NAME + " WHERE " + fieldName + "='"+value+"'", null);
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * Inserts the image the user selected when he first logged in
     * @param x int: The {@link Integer} instance of the image selected
     * @param id int: The {@link Integer} instance of the given id
     * @return The {@link Boolean} instance of the result of the query. Returns false if the query fails
     */
    public boolean insertImage(int x, int id){

        boolean imageSuccessful=false;

        ContentValues values = new ContentValues();
        values.put(USER_COLUMN_IMAGE, x);

        SQLiteDatabase db = this.getWritableDatabase();

        imageSuccessful = db.update(USER_TABLE_NAME,values, " id= " + id, null) > 0;

        return imageSuccessful;
    }

    /**
     * Gets the image that the user has selected when he first logged in
     * @param id String: The {@link String} instance of the id given
     * @return The {@link Cursor} instance of the query run
     */
    public Cursor getImage(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT " + USER_COLUMN_IMAGE+ " FROM " + USER_TABLE_NAME + " WHERE " + USER_COLUMN_ID+ "='"+id+"'",null);

        return res;
    }

}

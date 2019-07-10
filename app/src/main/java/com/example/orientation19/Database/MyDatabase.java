package com.example.orientation19.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.orientation19.MyPojos.UserDetails;

public class MyDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Orientation19";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "USER_LIST";
    private static final String USER_LOGGED_IN_STATUS = "USER_LOGGED_IN_STATUS";
    private static final String JWT_TOKEN = "JWT_TOKEN";
    private static final String ROLL_NUMBER = "ROLL_NUMBER";
    private static final String COLUMN_TIMESTAMP = "TIMESTAMP";
    private static final String COLUMN_ID = "id";


    private String[] allColumns = {USER_LOGGED_IN_STATUS, JWT_TOKEN, ROLL_NUMBER, COLUMN_TIMESTAMP, COLUMN_ID};

    private static final String CREATE_DB = "CREATE TABLE " + TABLE_NAME + "(" + USER_LOGGED_IN_STATUS + " TEXT, " + JWT_TOKEN + " TEXT,"+ROLL_NUMBER+" TEXT, " + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP, " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + ")";


    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // create table
        db.execSQL(CREATE_DB);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void insert(UserDetails l) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_LOGGED_IN_STATUS, l.getLoginStatus());
        contentValues.put(JWT_TOKEN, l.getJWTToken());
        contentValues.put(ROLL_NUMBER,l.getRollNumber());

        // insert row
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public int getDifferentItemsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        // return count
        return count;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public String getJwtToken() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        String jwt = "";

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                jwt = cursor.getString(1);
                cursor.close();
            }
        }

        db.close();
        return jwt;
    }

    public String getRollNumber() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        String jwt = "";

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                jwt = cursor.getString(2);
                cursor.close();
            }
        }

        db.close();
        return jwt;
    }
}
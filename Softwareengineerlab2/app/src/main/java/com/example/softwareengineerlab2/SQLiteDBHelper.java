package com.example.softwareengineerlab2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "bank";
    public static final String TABLE_NAME = "customer";
    public  static final String Table_names= "account";
    public static final int DB_VERSION = 1;
    public static String USER_NAME = "name", USER_ADDRESS = "Address", USER_PASS = "Id", USER_FULLNAME = "userFullName", PHONE_NUMBER = "phoneNumber", USER_LANG = "userLang";
    public static String CREATE_TABLE = "CREATE TABLE customer (Id text, name text, Address text)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS customer";
    public static final String TRUNCATE_TABLE = "DELETE FROM useraccount";

    public SQLiteDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public Cursor readAccount(SQLiteDatabase sqLiteDatabase) {
        String[] values = {USER_NAME, USER_ADDRESS, USER_PASS};
        Cursor cursor = sqLiteDatabase.query(Table_names, values, null, null, null, null, null);
        return cursor;
    }

    public void addAccount(String uName, String uPass, String uaddress, SQLiteDatabase sqLiteDatabase) {
        ContentValues cValues = new ContentValues();
        cValues.put(USER_NAME, uName);
        cValues.put(USER_PASS, uPass);
        cValues.put(USER_ADDRESS, uaddress);



        sqLiteDatabase.insert(TABLE_NAME, null, cValues);
    }
}

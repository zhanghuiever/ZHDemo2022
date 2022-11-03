package com.zh.demo.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME_COMPANY = "company.db";
    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_TABLE_EMPLOYEE = "employee";
    public final static String DATABASE_TABLE_MANAGER = "manager";
    public final static String DATABASE_COLUMN_ID = "id";
    public final static String DATABASE_COLUMN_NAME = "name";
    public final static String DATABASE_COLUMN_AGE = "age";
    public final static String DATABASE_COLUMN_ADDRESS = "address";
    public final static String DATABASE_COLUMN_SALARY = "salary";
    public final static String DATABASE_COLUMN_MANAGER_LEVEL = "level";


    public MySQLiteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME_COMPANY, null, DATABASE_VERSION);
    }

    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //公司雇员info
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_EMPLOYEE + "("
                + DATABASE_COLUMN_ID +  " INTEGER PRIMARY KEY, AUTOINCREMENT"
                + DATABASE_COLUMN_NAME +  " TEXT NOT NULL,"
                + DATABASE_COLUMN_AGE + " INT CHECK(" + DATABASE_COLUMN_AGE + ">=0),"
                + DATABASE_COLUMN_ADDRESS + " VARCHAR(50) DEFAULT 'CHINA',"
                + DATABASE_COLUMN_SALARY + " REAL)");

        //公司管理者info，比employee多了一个level列
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_MANAGER + "("
                + DATABASE_COLUMN_ID +  " INTEGER PRIMARY KEY, AUTOINCREMENT"
                + DATABASE_COLUMN_NAME +  " TEXT NOT NULL,"
                + DATABASE_COLUMN_AGE + " INT CHECK(" + DATABASE_COLUMN_AGE + ">=0),"
                + DATABASE_COLUMN_MANAGER_LEVEL + " INT CHECK(" + DATABASE_COLUMN_MANAGER_LEVEL + ">0),"
                + DATABASE_COLUMN_ADDRESS + " VARCHAR(50) DEFAULT 'CHINA',"
                + DATABASE_COLUMN_SALARY + " REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

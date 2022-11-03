package com.zh.demo.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

public class MyContentProvider extends ContentProvider {

    private Context mContext;
    private MySQLiteOpenHelper mySQLiteOpenHelper = null;
    private SQLiteDatabase mSQLiteDatabase = null;

    public final static String AUTHORITY = "com.zh.demo.provider";

    public final static int EMPLOYEE_CODE = 1;
    public final static int MANAGER_CODE = 2;
    public final static int EMPLOYEE_MULTI_CODE = 3;
    public final static int MANAGER_MULTI_CODE = 4;

    private final static UriMatcher mMatch;

    static {
        mMatch = new UriMatcher(UriMatcher.NO_MATCH);
        // 用于CRUD
        mMatch.addURI(AUTHORITY, MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE + "/#", EMPLOYEE_CODE);
        mMatch.addURI(AUTHORITY, MySQLiteOpenHelper.DATABASE_TABLE_MANAGER + "/#", MANAGER_CODE);
        // 用于getType()
        mMatch.addURI(AUTHORITY, MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE, EMPLOYEE_MULTI_CODE);
        mMatch.addURI(AUTHORITY, MySQLiteOpenHelper.DATABASE_TABLE_MANAGER, MANAGER_MULTI_CODE);
    }

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {

        mContext = getContext();
        mySQLiteOpenHelper = new MySQLiteOpenHelper(mContext);
        mSQLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();

        //插入一些数据，用于测试
        //在employee中添加数据，添加数据前清空表
        mSQLiteDatabase.execSQL("delete from " + MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE);
        mSQLiteDatabase.execSQL("insert into " + MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE +
                "(id, name, age, address, salary) " +
                " values(1, 'Lydia', 18, 'China', 5000.0)");
        mSQLiteDatabase.execSQL("insert into " + MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE +
                "(id, name, age, address, salary) " +
                " values(2, 'Tom', 50, 'American', 3000.0)");
        mSQLiteDatabase.execSQL("insert into " + MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE +
                "(id, name, age, address, salary) " +
                " values(3, '张三', 68, 'Tianjin', 10000.0)");
        mSQLiteDatabase.execSQL("insert into " + MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE +
                "(id, name, age, address, salary) " +
                " values(4, '李四', 55, 'Shanghai', 9500.0)");
        mSQLiteDatabase.execSQL("insert into " + MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE +
                "(id, name, age, address, salary) " +
                " values(5, '王五', 47, 'Beijing', 6000.0)");
        mSQLiteDatabase.execSQL("insert into " + MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE +
                "(id, name, age, address, salary) " +
                " values(6, 'Monica', 31, 'NewYork', 20000.0)");
        mSQLiteDatabase.execSQL("insert into " + MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE +
                "(id, name, age, address, salary) " +
                " values(7, 'Chandle', 31, 'NewYork', 20000.0)");
        mSQLiteDatabase.execSQL("insert into " + MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE +
                "(id, name, age, address, salary) " +
                " values(8, '张三', 25, 'China', 17000.0)");
        mSQLiteDatabase.execSQL("insert into " + MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE +
                "(id, name, age, address, salary) " +
                " values(9, 'Tom', 75, 'America', 7000.0)");

        //在manager中添加数据，添加数据前清空表
        mSQLiteDatabase.execSQL("delete from " + MySQLiteOpenHelper.DATABASE_TABLE_MANAGER);
        mSQLiteDatabase.execSQL("insert into " +
                MySQLiteOpenHelper.DATABASE_TABLE_MANAGER +
                "(id, name, age, address, salary, level) " +
                "values(1, 'Monica', 55, 'America', 37000.0, 1)");
        mSQLiteDatabase.execSQL("insert into " +
                MySQLiteOpenHelper.DATABASE_TABLE_MANAGER +
                "(id, name, age, address, salary, level) " +
                "values(2, '张三', 45, 'China', 50000.0, 2)");
        mSQLiteDatabase.execSQL("insert into " +
                MySQLiteOpenHelper.DATABASE_TABLE_MANAGER +
                "(id, name, age, address, salary, level) " +
                "values(3, '王五', 78, 'China', 150000.0, 3)");
        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        String tableName = getTableName(uri);
        int result = mSQLiteDatabase.delete(tableName, selection, selectionArgs);
        Toast.makeText(mContext, "delete result: " + result, Toast.LENGTH_SHORT).show();

        mContext.getContentResolver().notifyChange(uri, null);
        return result;
    }

    @Override
    public String getType(Uri uri) {
        switch (mMatch.match(uri)) {
            case EMPLOYEE_CODE:
                return "vnd.android.cursor.item/vnd.zh.employee";
            case EMPLOYEE_MULTI_CODE:
                return "vnd.android.cursor.dir/vnd.zh.employee";
            case MANAGER_CODE:
                return "vnd.android.cursor.item/vnd.zh.manager";
            case MANAGER_MULTI_CODE:
                return "vnd.android.cursor.dir/vnd.zh.manager";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        // 向该表添加数据
        String tableName = getTableName(uri);
        long result = mSQLiteDatabase.insert(tableName, null, values);
        Toast.makeText(mContext, "insert result: " + result, Toast.LENGTH_SHORT).show();

        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        mContext.getContentResolver().notifyChange(uri, null);

        return uri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // 这里不区分uri是item还是dir, 用户想得到什么的data，可以通过query的参数，自己去设定
        String tableName = getTableName(uri);
        return mSQLiteDatabase.query(tableName, projection,
                selection, selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        String tableName = getTableName(uri);
        int result = mSQLiteDatabase.update(tableName, values, selection, selectionArgs);
        Toast.makeText(mContext, "update result: " + result, Toast.LENGTH_SHORT).show();

        return result;
    }

    private String getTableName(Uri uri) {
        String tableName = null;
        switch (mMatch.match(uri)) {
            // content://com.zh.demo.provider/employee和content://com.zh.demo.provider/employee/#
            // 都能正常的访问的该ContentProvider
            case EMPLOYEE_CODE:
            case EMPLOYEE_MULTI_CODE:
                tableName = MySQLiteOpenHelper.DATABASE_TABLE_EMPLOYEE;
                break;
            case MANAGER_CODE:
            case MANAGER_MULTI_CODE:
                tableName = MySQLiteOpenHelper.DATABASE_TABLE_MANAGER;
                break;
            default:
        }
        return tableName;
    }
}
package com.example.richard.sqllitetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Richard on 4/10/2017.
 */

public class DBStructure {

    public static abstract class tableEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_ID = "userid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DOB = "dob";
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "users.db";
    private final Context context;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + DBStructure.tableEntry.TABLE_NAME + " (" + DBStructure.tableEntry._ID + " INTEGER PRIMARY KEY," + DBStructure.tableEntry.COLUMN_ID + TEXT_TYPE + COMMA_SEP +
            DBStructure.tableEntry.COLUMN_NAME + TEXT_TYPE + COMMA_SEP + DBStructure.tableEntry.COLUMN_DOB + TEXT_TYPE + " );";
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DBStructure.tableEntry.TABLE_NAME;

    private MySQLiteOpenHelper myDBHelper;
    private SQLiteDatabase db;

    private String[] projection = {DBStructure.tableEntry.COLUMN_ID, DBStructure.tableEntry.COLUMN_NAME, DBStructure.tableEntry.COLUMN_DOB};

    private class DBManager {

        public DBManager(Context ctx) {
            this.context = ctx;
            myDBHelper = new MySQLiteOpenHelper(context);
        }

        public boolean updateUser(String id, String name) {

            ContentValues values = new ContentValues();
            values.put(DBStructure.tableEntry.COLUMN_NAME, name);
            String selection = DBStructure.tableEntry.COLUMN_ID + " LIKE ?";
            String[] selectionArgs = {String.valueOf(id)};
            int count = db.update(
                    DBStructure.tableEntry.TABLE_NAME, values, selection, selectionArgs);

            return count > 0;
        }
    }

    private static class MySQLiteOpenHelper extends SQLiteOpenHelper {

        public MySQLiteOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }

        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
    }

    public DBManager open() throws SQLException {
        db = myDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        myDBHelper.close();
    }

    public long insertUser(String id, String name, String dob) {
        ContentValues values = new ContentValues();
        values.put(DBStructure.tableEntry.COLUMN_ID, id);
        values.put(DBStructure.tableEntry.COLUMN_NAME, name);
        values.put(DBStructure.tableEntry.COLUMN_DOB, dob);
        return db.insert(DBStructure.tableEntry.TABLE_NAME, null, values);
    }

    public Cursor getAllUsers() {
        return db.query(DBStructure.tableEntry.TABLE_NAME, projection, null, null, null, null, null);
    }

    public int deleteUser(String rowId) {
        String[] selectionArgs = {String.valueOf(rowId)};
        String selection = DBStructure.tableEntry.COLUMN_ID + " LIKE ?";
        return db.delete(DBStructure.tableEntry.TABLE_NAME, selection, selectionArgs);
    }
}

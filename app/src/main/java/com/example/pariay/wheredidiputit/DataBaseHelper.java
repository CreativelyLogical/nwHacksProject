package com.example.pariay.wheredidiputit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pariay on 2018-01-13.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseHelper";

    private static final String TABLE_NAME = "item_table";
    private static final String COL0 = "ID";
    private static final String COL1 = "name";
    private static final String COL2 = "location";

    public DataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL1 + " TEXT, " + COL2 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        contentValues.put(COL2, location);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemId(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getRow(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}

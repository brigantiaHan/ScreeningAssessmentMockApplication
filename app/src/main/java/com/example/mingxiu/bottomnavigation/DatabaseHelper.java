package com.example.mingxiu.bottomnavigation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mingxiu on 6/14/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "USERS.db";
    public static final String TABLE_NAME = "info_table";
    public static final String COL_0 = "id";
    public static final String COL_1 = "name";
    public static final String COL_2 = "code";
    public static final String COL_3 = "color";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (id integer primary key autoincrement,name text,code text,color text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String code,String color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, code);
        contentValues.put(COL_3, color);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result == -1 ? false : true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " order by id desc", null);
        return res;
    }
}

package com.rec.ct.rec_txt.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by SW on 3/15/16.
 */
public class P2SDBHelper  extends SQLiteOpenHelper {

    public static final String TABLE_RECTIFIERS = "rectifiers";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_UUID = "_uuid";
    public static final String COLUMN_PHONE = "_phone";

    private static final String DATABASE_NAME = "rectifiers.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_RECTIFIERS + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            +COLUMN_UUID+" text not null, "+
            COLUMN_NAME + " text not null, "+
            COLUMN_PHONE + " text not null "+
            ");";

    public P2SDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(P2SDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECTIFIERS);
        onCreate(db);
    }

}
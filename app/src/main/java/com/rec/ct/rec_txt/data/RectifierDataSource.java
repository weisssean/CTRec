package com.rec.ct.rec_txt.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rec.ct.rec_txt.Rectifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SW on 3/15/16.
 */
public class RectifierDataSource {

    private SQLiteDatabase database;
    private P2SDBHelper dbHelper;
    private String[] allColumns = {P2SDBHelper.COLUMN_ID, P2SDBHelper.COLUMN_UUID,
            P2SDBHelper.COLUMN_NAME,
            P2SDBHelper.COLUMN_PHONE};

    public RectifierDataSource(Context context) {
        dbHelper = new P2SDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Rectifier createRectifier(Rectifier rectifier) {
        ContentValues values = Rectifier.toValues(rectifier);
        long insertId = database.insert(P2SDBHelper.TABLE_RECTIFIERS, null,
                values);
        Cursor cursor = database.query(P2SDBHelper.TABLE_RECTIFIERS,
                allColumns, P2SDBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Rectifier newRec = cursorToRectifier(cursor);
        cursor.close();
        return newRec;
    }

    public void deleteRectifier(Rectifier rectifier) {
        String id = rectifier.getUUID();
        System.out.println("rec deleted with id: " + id);
        database.delete(P2SDBHelper.TABLE_RECTIFIERS, P2SDBHelper.COLUMN_UUID
                + " = " + id, null);
    }

    public List<Rectifier> getAllRectifiers() {
        List<Rectifier> rectifiers = new ArrayList<Rectifier>();

        Cursor cursor = database.query(P2SDBHelper.TABLE_RECTIFIERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Rectifier rectifier = cursorToRectifier(cursor);
            rectifiers.add(rectifier);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return rectifiers;
    }


    public Cursor getAllRectifiersCursor() {

        Cursor cursor = database.query(P2SDBHelper.TABLE_RECTIFIERS,
                allColumns, null, null, null, null, null);
        return cursor;
    }

    public Rectifier cursorToRectifier(Cursor cursor) {
        try {

            Rectifier rectifier = new Rectifier();
            rectifier.setId(cursor.getString(cursor.getColumnIndex(P2SDBHelper.COLUMN_UUID)));
            rectifier.setName(cursor.getString(cursor.getColumnIndexOrThrow(P2SDBHelper.COLUMN_NAME)));
            rectifier.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(P2SDBHelper.COLUMN_PHONE)));
            Log.d("REC",rectifier.toString());
            return rectifier;
        } catch (Exception e) {
            Log.e(RectifierDataSource.class.getName(), "", e);
        }
        return null;
    }

}

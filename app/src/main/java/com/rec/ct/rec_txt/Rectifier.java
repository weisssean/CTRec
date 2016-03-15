package com.rec.ct.rec_txt;

import android.content.ContentValues;

import com.rec.ct.rec_txt.data.P2SDBHelper;

import java.util.UUID;

/**
 * Created by SW on 3/15/16.
 */
public class Rectifier {
    private String uuid;
    private String name;
    private String phone_number;

    public Rectifier(){
        uuid  = UUID.randomUUID().toString();
    }

    public Rectifier(String  _name, String _phone_number){
        uuid  = UUID.randomUUID().toString();
        name = _name;
        phone_number= _phone_number;
    }

    public void setId(String  id) {
        this.uuid = id;
    }

    public String getUUID() {
        return uuid;
    }

    public String getPhoneNumber() {
        return phone_number;
    }
    public void setPhoneNumber(String _pn) {
        phone_number = _pn;
    }

    public String getName() {
        return name;
    }
    public void setName(String _n) {
        name = _n;
    }

    public static ContentValues toValues(Rectifier rectifier) {
        ContentValues values = new ContentValues();

        values.put(P2SDBHelper.COLUMN_UUID, rectifier.getUUID());
        values.put(P2SDBHelper.COLUMN_NAME, rectifier.getName());
        values.put(P2SDBHelper.COLUMN_PHONE, rectifier.getPhoneNumber());
        return values;
    }
}

package com.rec.ct.rec_txt;

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

}

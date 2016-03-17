package com.rec.ct;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.rec.ct.rec_txt.MainActivity;

/**
 * Created by SW on 3/17/16.
 */
public class SmsListener extends BroadcastReceiver {

        private SharedPreferences preferences;

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub

            if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
                SmsMessage[] msgs = null;
                String msg_from;
                if (bundle != null){
                    //---retrieve the SMS message received---
                    try{
                        Object[] pdus = (Object[]) bundle.get("pdus");
                        msgs = new SmsMessage[pdus.length];
                        for(int i=0; i<msgs.length; i++){
                            msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                            msg_from = msgs[i].getOriginatingAddress();
                            String msgBody = msgs[i].getMessageBody();
                            Log.d(MainActivity.class.getName(), msg_from);
                            Log.d(MainActivity.class.getName(),msgBody);


                            //Toast.makeText(getApplicationContext(),"got sms",Toast.LENGTH_LONG).show();
                            //mDatasource.createRectifier(new Rectifier(msg_from,msgBody));
                        }
                    }catch(Exception e){
                            Log.d("Exception caught",e.getMessage());
                    }
                }
            }
        }

}

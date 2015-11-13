package com.netease.lcd.lcdtestcases.cases.permission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.netease.lcd.lcdtestcases.R;

public class MVersionPermissionTest extends AppCompatActivity {

    PhoneStateListener listener = new PhoneStateListener(){
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mversion_permission_test);
        findViewById(R.id.actCall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:13507146879")));
                //saveNewContact("13507146879","ChandarLee");
                //listenPhoneState();
                registerReceiver();
            }
        });
    }

    /**
     * 保存到新建联系人
     */
    public void saveNewContact(final String mobile,final String name) {
        Intent it = new Intent(Intent.ACTION_INSERT, Uri.withAppendedPath(Uri.parse("content://com.android.contacts"),
                "contacts"));
        it.setType("vnd.android.cursor.dir/person");
        if(!TextUtils.isEmpty(name)) {
            // 联系人姓名
            it.putExtra(android.provider.ContactsContract.Intents.Insert.NAME, name);
        }
        // 电话号码
        it.putExtra(android.provider.ContactsContract.Intents.Insert.PHONE, mobile);
        startActivity(it);
    }

    private void listenPhoneState(){
        ((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((TelephonyManager)getSystemService(TELEPHONY_SERVICE)).listen(listener, PhoneStateListener.LISTEN_NONE);
    }

    private void registerReceiver(){
       /* BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                abortBroadcast();
            }
        };*/
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(new ReceivingSMSReceiver(),filter);
    }

    public class ReceivingSMSReceiver extends BroadcastReceiver {
        private static final String SMS_RECEIVED ="android.provider.Telephony.SMS_RECEIVED";
        private static final String TAG = "ReceivingSMSReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SMS_RECEIVED)) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    for (int i = 0; i < pdus.length; i++)
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    for (SmsMessage message : messages) {
                        String msg = message.getMessageBody();
                        Log.i(TAG, msg);
                        String to = message.getOriginatingAddress();
                        Log.i(TAG, to);
                    }
                }
            }
        }
    }
}

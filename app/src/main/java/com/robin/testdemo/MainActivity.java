package com.robin.testdemo;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;

import com.robin.testdemo.activityjump.ActivityA;
import com.robin.testdemo.okhttpTest.OkhttpTest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    IntentFilter filter;
    SmsReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ActivityA.class);
                startActivity(intent);
            }
        });
        filter=new IntentFilter();


        filter.addAction("android.provider.Telephony.SMS_RECEIVED" );
        receiver=new SmsReceiver();
        registerReceiver(receiver,filter);//注册广播接收器


    }

    public class SmsReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
        String action = intent == null ? null : intent.getAction();

        // If on KitKat+ and default messaging app then look for new deliver actions actions.
        if (true) {

            if (Telephony.Sms.Intents.SMS_DELIVER_ACTION.equals(action)) {
                handleIncomingSms(context, intent);
            } else if (Telephony.Sms.Intents.WAP_PUSH_DELIVER_ACTION.equals(action)) {
                handleIncomingMms(context, intent);
            }
        } else { // Otherwise look for old pre-KitKat actions
            if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(action)) {
                handleIncomingSms(context, intent);
            } else if (Telephony.Sms.Intents.WAP_PUSH_RECEIVED_ACTION.equals(action)) {
                handleIncomingMms(context, intent);
            }
        }
    }

    private void handleIncomingSms(Context context, Intent intent) {
        // TODO: Handle SMS here
//        // As an example, we'll start a wakeful service to handle the SMS
//        intent.setAction(MessagingService.ACTION_MY_RECEIVE_SMS);
//        intent.setClass(context, MessagingService.class);
//        startWakefulService(context, intent);
        Log.i(TAG, "handleIncomingSms: "+intent.toString());
    }

    private void handleIncomingMms(Context context, Intent intent) {
        // TODO: Handle MMS here
        // As an example, we'll start a wakeful service to handle the MMS
//        intent.setAction(MessagingService.ACTION_MY_RECEIVE_MMS);
//        intent.setClass(context, MessagingService.class);
//        startWakefulService(context, intent);
    }
    }
//private SmsObserver smsObserver;
//    private final Handler smsHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        smsObserver = new SmsObserver(smsHandler);
//        getContentResolver().registerContentObserver(Uri.parse("content://sms/"), true, smsObserver);
//
//    }
//
//
//    class SmsObserver extends ContentObserver {
//        private Uri uri;
//
//        public SmsObserver(Handler handler) {
//            super(handler);
//        }
//
//        @SuppressLint("LongLogTag")
//        @Override
//        public void onChange(boolean selfChange, Uri uri) {
//            super.onChange(selfChange);
//            if (uri.toString().equals("content://sms/raw")) {
//                return;
//            }
//            final String SMS_URI_ALL = "content://sms/"; // 所有短信
//            final String SMS_URI_INBOX = "content://sms/inbox"; // 收件箱
//            final String SMS_URI_SEND = "content://sms/sent"; // 已发送
//            final String SMS_URI_DRAFT = "content://sms/draft"; // 草稿
//            final String SMS_URI_OUTBOX = "content://sms/outbox"; // 发件箱
//            final String SMS_URI_FAILED = "content://sms/failed"; // 发送失败
//            final String SMS_URI_QUEUED = "content://sms/queued"; // 待发送列表
//
//
//            StringBuilder smsBuilder = new StringBuilder();
//
//            try {
//                String[] projection = new String[] { "_id", "address", "person",
//                        "body", "date", "type", };
////            Cursor cur = getContentResolver().query(uri, projection, null,
////                    null, "date desc"); // 获取手机内部短信
//                // 获取短信中最新的未读短信
//                Cursor cur = getContentResolver().query(uri, projection,
//                        "read = ?", new String[]{"0"}, "date desc");
//                if (cur.moveToFirst()) {
//                    int index_Address = cur.getColumnIndex("address");
//                    int index_Person = cur.getColumnIndex("person");
//                    int index_Body = cur.getColumnIndex("body");
//                    int index_Date = cur.getColumnIndex("date");
//                    int index_Type = cur.getColumnIndex("type");
//
//                    do {
//                        String strAddress = cur.getString(index_Address);
//                        int intPerson = cur.getInt(index_Person);
//                        String strbody = cur.getString(index_Body);
//                        long longDate = cur.getLong(index_Date);
//                        int intType = cur.getInt(index_Type);
//
//                        SimpleDateFormat dateFormat = new SimpleDateFormat(
//                                "yyyy-MM-dd hh:mm:ss");
//                        Date d = new Date(longDate);
//                        String strDate = dateFormat.format(d);
//
//                        String strType = "";
//                        if (intType == 1) {
//                            strType = "接收";
//                        } else if (intType == 2) {
//                            strType = "发送";
//                        } else if (intType == 3) {
//                            strType = "草稿";
//                        } else if (intType == 4) {
//                            strType = "发件箱";
//                        } else if (intType == 5) {
//                            strType = "发送失败";
//                        } else if (intType == 6) {
//                            strType = "待发送列表";
//                        } else if (intType == 0) {
//                            strType = "所以短信";
//                        } else {
//                            strType = "null";
//                        }
//
//                        smsBuilder.append("[ ");
//                        smsBuilder.append(strAddress + ", ");
//                        smsBuilder.append(intPerson + ", ");
//                        smsBuilder.append(strbody + ", ");
//                        smsBuilder.append(strDate + ", ");
//                        smsBuilder.append(strType);
//                        smsBuilder.append(" ]\n\n");
//                    } while (cur.moveToNext());
//
//                    if (!cur.isClosed()) {
//                        cur.close();
//                        cur = null;
//                    }
//                } else {
//                    smsBuilder.append("no result!");
//                }
//
//                smsBuilder.append("getSmsInPhone has executed!");
//
//            } catch (SQLiteException ex) {
//                Log.d("SQLiteException in getSmsInPhone", ex.getMessage());
//            }
//
//            Log.i(TAG, "onChange: "+smsBuilder.toString());
//        }
//    }
}

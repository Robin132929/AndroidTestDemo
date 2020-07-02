package com.robin.testdemo.activityjump;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.robin.testdemo.MainActivity;
import com.robin.testdemo.R;
import com.robin.testdemo.collection.TestQueue;
import com.robin.testdemo.collection.TestSet;
import com.robin.testdemo.execption.TestExecption;
import com.robin.testdemo.io.Testio;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * a跳转到b 生命周期 onpasue -->oncreate -->onstart -->onresume -->onstop --> onSaveInstanceState
 *                    a          b           b          b           a                a
 *
 *  此处还有一个地方需要注意 onSaveInstanceState的调用时机 ：
 *                                             1、 api < 11，onSaveInstance在onPause之前执行
 *                                             2、11 <= api < 28，onSaveInstance在onPause之后，onStop之前执行
 *                                             3、api >= 28，onSaveInstance在onStop之后执行
 *
 */
public class ActivityA extends Activity {
    private static final String TAG = "Test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        TestQueue.TestArrayDeque();

        TestExecption testExecption=new TestExecption();
        try {
            testExecption.TestExecption();
        } catch (TestExecption.MyExecption myExecption) {
            myExecption.printStackTrace();
        }
        Log.i(TAG, "onCreate: "+TestExecption.TestFinally());

        Testio.TestFile(this);
        Testio.TestSer(this);
        Testio.TestParce();
//        testExecption.TestRuntimeExecption();
        findViewById(R.id.bt_jump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityA.this, ActivityA.class);
                startActivity(intent);
            }
        });
    }

//    private static final int NOT_NOTICE = 2;//如果勾选了不再询问
//    private AlertDialog alertDialog;
//    private AlertDialog mDialog;
//    TextView tv;
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        ScrollView sv = new ScrollView(this);
//        setContentView(sv);
//         tv = new TextView(this);
//         tv.setText("  TEST  ");
//                sv.addView(tv);
//
//        myRequetPermission();
//
//    }
//    @TargetApi(Build.VERSION_CODES.M)
//    private void myRequetPermission() {
//        if (getApplication().checkSelfPermission( Manifest.permission.READ_SMS) == PERMISSION_GRANTED) {
//            tv.setText(getSmsInPhone());
//        }else {
//
//            Toast.makeText(this,"您未申请权限!",Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if (requestCode == 1) {
//            tv.setText(getSmsInPhone());
//        }else {
//            Log.i("TAG", "onRequestPermissionsResult:  no permission");
//        }
//    }
//
//    @SuppressLint("LongLogTag")
//    public String getSmsInPhone() {
//        final String SMS_URI_ALL = "content://sms/"; // 所有短信
//        final String SMS_URI_INBOX = "content://sms/inbox"; // 收件箱
//        final String SMS_URI_SEND = "content://sms/sent"; // 已发送
//        final String SMS_URI_DRAFT = "content://sms/draft"; // 草稿
//        final String SMS_URI_OUTBOX = "content://sms/outbox"; // 发件箱
//        final String SMS_URI_FAILED = "content://sms/failed"; // 发送失败
//        final String SMS_URI_QUEUED = "content://sms/queued"; // 待发送列表
//
//        StringBuilder smsBuilder = new StringBuilder();
//
//        try {
//            Uri uri = Uri.parse(SMS_URI_ALL);
//            String[] projection = new String[] { "_id", "address", "person",
//                    "body", "date", "type", };
////            Cursor cur = getContentResolver().query(uri, projection, null,
////                    null, "date desc"); // 获取手机内部短信
//            // 获取短信中最新的未读短信
//             Cursor cur = getContentResolver().query(uri, projection,
//             "read = ?", new String[]{"0"}, "date desc");
//            if (cur.moveToFirst()) {
//                int index_Address = cur.getColumnIndex("address");
//                int index_Person = cur.getColumnIndex("person");
//                int index_Body = cur.getColumnIndex("body");
//                int index_Date = cur.getColumnIndex("date");
//                int index_Type = cur.getColumnIndex("type");
//
//                do {
//                    String strAddress = cur.getString(index_Address);
//                    int intPerson = cur.getInt(index_Person);
//                    String strbody = cur.getString(index_Body);
//                    long longDate = cur.getLong(index_Date);
//                    int intType = cur.getInt(index_Type);
//
//                    SimpleDateFormat dateFormat = new SimpleDateFormat(
//                            "yyyy-MM-dd hh:mm:ss");
//                    Date d = new Date(longDate);
//                    String strDate = dateFormat.format(d);
//
//                    String strType = "";
//                    if (intType == 1) {
//                        strType = "接收";
//                    } else if (intType == 2) {
//                        strType = "发送";
//                    } else if (intType == 3) {
//                        strType = "草稿";
//                    } else if (intType == 4) {
//                        strType = "发件箱";
//                    } else if (intType == 5) {
//                        strType = "发送失败";
//                    } else if (intType == 6) {
//                        strType = "待发送列表";
//                    } else if (intType == 0) {
//                        strType = "所以短信";
//                    } else {
//                        strType = "null";
//                    }
//
//                    smsBuilder.append("[ ");
//                    smsBuilder.append(strAddress + ", ");
//                    smsBuilder.append(intPerson + ", ");
//                    smsBuilder.append(strbody + ", ");
//                    smsBuilder.append(strDate + ", ");
//                    smsBuilder.append(strType);
//                    smsBuilder.append(" ]\n\n");
//                } while (cur.moveToNext());
//
//                if (!cur.isClosed()) {
//                    cur.close();
//                    cur = null;
//                }
//            } else {
//                smsBuilder.append("no result!");
//            }
//
//            smsBuilder.append("getSmsInPhone has executed!");
//
//        } catch (SQLiteException ex) {
//            Log.d("SQLiteException in getSmsInPhone", ex.getMessage());
//        }
//
//        return smsBuilder.toString();
//    }
}

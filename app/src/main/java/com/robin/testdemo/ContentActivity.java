package com.robin.testdemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.robin.testdemo.aidl.AddService;
import com.robin.testdemo.aidl.IMyAidlInterface;

public class ContentActivity extends Activity {
    private static final String TAG = "ContentActivity";

    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
         bindService(new Intent(this, AddService.class), new ServiceConnection() {
             @Override
             public void onServiceConnected(ComponentName name, IBinder service) {
                 IMyAidlInterface Addservvice=IMyAidlInterface.Stub.asInterface(service);
             }

             @Override
             public void onServiceDisconnected(ComponentName name) {

             }
         },BIND_AUTO_CREATE);
        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.robin.androidtest/table1");
                ContentValues values = new ContentValues();
                values.put("name", "aaaaaaa" + i++);
                values.put("author", "robin");
                values.put("price", 11.22);

                Uri newuri = getContentResolver().insert(uri, values);
//               String newid=newuri.getPathSegments().get(1);
//                for (int i1 = 0; i1 < newuri.getPathSegments().size(); i1++) {
//                                    Log.i(TAG, "onClick: "+newuri.getPathSegments().get(i));
//
//                }

                Log.i(TAG, "onClick: " + newuri.toString());
            }
        });

        findViewById(R.id.qury).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://com.robin.androidtest/table1");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        Log.i(TAG, "onClick  qury : " + name);
                    }
                }
            }
        });


    }

//    public class Stub extends android.os.Binder implements IPlusServer {
//
//        public IPlusServer asInterface(android.os.IBinder obj)
//        {
//            if ((obj==null)) {
//                return null;
//            }
//            android.os.IInterface iin = obj.queryLocalInterface("IPlus server");
//            if (((iin!=null)&&(iin instanceof com.robin.testdemo.aidl.IMyAidlInterface))) {
//                return ((com.robin.testdemo.aidl.IMyAidlInterface)iin);
//            }
//            return new Proxy(obj);
//        }
//
//        @Override
//        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
//            switch (code) {
//                case INTERFACE_TRANSACTION: {
//                    reply.writeString(descriptor);
//                    return true;
//                }
//                case TRANSACTION_add: {
//                    data.enforceInterface(descriptor);
//                    int _arg0;
//                    _arg0 = data.readInt();
//                    int _arg1;
//                    _arg1 = data.readInt();
//                    int _result = this.add(_arg0, _arg1);
//                    reply.writeNoException();
//                    reply.writeInt(_result);
//                    return true;
//                }
//                default: {
//                    return super.onTransact(code, data, reply, flags);
//                }
//            }
//        }
//    }
}

package com.robin.testdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.robin.testdemo.activityjump.ActivityA;
import com.robin.testdemo.activityjump.ActivityB;

public class MySerivce extends Service {
    private static final String TAG = "MySerivce";
    final Messenger messenger=new Messenger(new MyHandler());

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: robin test");
        NotificationChannel notificationChannel = null;
        NotificationManager notificationManager=null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            notificationChannel = new NotificationChannel("200", "robin", NotificationManager.IMPORTANCE_HIGH);
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Intent intent1 = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0, intent1, 0);

        Notification notification=new Notification.Builder(this,"200")
                .setContentTitle("robin test")
                .setContentText("robin test 123")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)//8.0以上必须设置否则不显示通知
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);
        startActivity(new Intent(this, ActivityB.class));
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    class MyHandler extends Handler{
        public MyHandler() {
            super();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.i(TAG, "handleMessage: "+msg.what);
        }
    }
}

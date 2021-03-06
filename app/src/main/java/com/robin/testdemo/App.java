package com.robin.testdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.jumpraw.ad.GCAdSdk;


public class App extends Application implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "TestApp";
    public static Context context;

    public static String AppID = "10000";

    //rabbit
    public static String FullscreenSlotID = "945279212";
    public static String RewardVideoSlotID = "945105337";
    @Override
    public void onCreate() {
        super.onCreate();
        this.registerActivityLifecycleCallbacks(this);
        context=this;
        GCAdSdk.init(AppID);
//        this.startActivity();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        GCAdSdk.attachBaseContext(base);

    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated: "+activity.getComponentName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(TAG, "onActivityStarted: "+activity.getComponentName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(TAG, "onActivityResumed: "+activity.getComponentName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(TAG, "onActivityPaused: "+activity.getComponentName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(TAG, "onActivityStopped: "+activity.getComponentName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.d(TAG, "onActivitySaveInstanceState: "+activity.getComponentName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG, "onActivityDestroyed: "+activity.getComponentName());
    }

    public static Context get(){
        return context;
    }
}

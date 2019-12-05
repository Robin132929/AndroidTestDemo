package com.robin.testdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.robin.testdemo.utils.Logger.AndroidLogAdapter;
import com.robin.testdemo.utils.Logger.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
             Logger.i("onActivityCreated --- "+activity.getComponentName());
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                Logger.i("onActivityStarted --- "+activity.getComponentName());

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                Logger.i("onActivityResumed --- "+activity.getComponentName());

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                Logger.i("onActivityPaused --- "+activity.getComponentName());

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                Logger.i("onActivityStopped --- "+activity.getComponentName());

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
                Logger.i("onActivitySaveInstanceState --- "+activity.getComponentName());

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Logger.i("onActivityDestroyed --- "+activity.getComponentName());

            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}

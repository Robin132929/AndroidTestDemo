package com.robin.androidxorsupport;

import android.Manifest;
import android.content.Context;

import android.util.Log;


import androidx.core.content.ContextCompat;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class TestAndroidxorSipport {
    private static final String TAG = "TestAndroidxorSipport";

    public static void Test(Context context){
        //                if (context.checkCallingOrSelfPermission(DEFAULT_PRESSION.get(i)) == PERMISSION_GRANTED) {
//                    result = (int) (result + Math.pow(2, i));
//                }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PERMISSION_GRANTED) {
            Log.i(TAG, "Test:  success");
        }else {
            Log.i(TAG, "Test: failed");
        }
    }
}

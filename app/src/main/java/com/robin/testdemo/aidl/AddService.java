package com.robin.testdemo.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AddService extends Service {
    public AddService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
       return new IMyAidlInterface.Stub() {
           @Override
           public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

           }

           @Override
           public int add(int a, int b) throws RemoteException {
               return a+b;
           }
       };

    }
}

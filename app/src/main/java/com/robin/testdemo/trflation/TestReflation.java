package com.robin.testdemo.trflation;

import android.util.Log;

public class TestReflation extends Testda implements TestInterface{
    private static final String TAG = "TestReflation";
    String q="1";
    private String w="2";
    public String e="3";

    private String getQ(){
        return "qqq";
    }

    public void setW(String w) {
        this.w = w;
    }

    @Override
    public void methodInterface(int i) {
        Log.i(TAG, "methodInterface: "+i);
    }

    @Override
    public String getDef() {
        return super.getDef();
    }
}

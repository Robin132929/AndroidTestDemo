package com.robin.testdemo.execption;

import android.util.Log;

public class TestExecption {
    private static final String TAG = "TestExecption";
    public void TestRuntimeExecption(){
        throw new MyRuntimeExecption("test RuntimeExecption");
    }

    public  void TestExecption() throws MyExecption {
        MyExecption ex=new MyExecption("test Execption");
        ex.initCause(new NullPointerException());
        throw ex;
    }
    public  class  MyRuntimeExecption extends RuntimeException{
        public MyRuntimeExecption(String message) {
            super(message);
        }
    }

    public class MyExecption extends Exception{
        public MyExecption(String message) {
            super(message);
        }
    }


    public static int TestFinally(){
        int i=10;
        try{
            i=12;
            Log.i(TAG, "TestFinally: try "+i);
            return i+=10;
        }catch (Exception e){
            Log.i(TAG, "TestFinally: catch");
        }finally {

            Log.i(TAG, "TestFinally: finally "+i);

        }
        return 0;
    }
}

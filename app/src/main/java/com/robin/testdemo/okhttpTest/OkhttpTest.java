package com.robin.testdemo.okhttpTest;

import okhttp3.OkHttpClient;

public class OkhttpTest {
//   static  instance;
   private static class build{
       static OkHttpClient.Builder instance=new OkHttpClient.Builder();
    }
    public static OkHttpClient.Builder getInstance(){
       return build.instance;
    }
}

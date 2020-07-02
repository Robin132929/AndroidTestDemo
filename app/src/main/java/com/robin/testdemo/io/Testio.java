package com.robin.testdemo.io;

import android.content.Context;
import android.os.Parcel;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;

public class Testio {
    private static final String TAG = "Testio";
    static File file = new File("abc");
    static Writer Re;
    static Reader reader;

    public static void TestFile(Context context) {
        File file = new File(context.getFilesDir(), "testio.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bean bean = new Bean();
        bean.setIndex(100);
        bean.setName("number");
        Bean.state = 120;
        ObjectOutputStream objectOutputStream = null;
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(bean);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void TestSer(Context context) {
        File file = new File(context.getFilesDir(), "testio.txt");
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));
            Bean bean = (Bean) inputStream.readObject();
            if (bean != null) {
                Log.i(TAG, "TestSer: " + bean.getIndex() + "  " + bean.getName() + "  " + Bean.state);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void TestParce(){
        //获取一个Parcel容器
        Parcel parcel = Parcel.obtain();
        //需要序列化的对象
        ABean bean = new ABean();
        bean.setIndex(110);
        bean.setName("number");
        //把对象写入Parcel
        parcel.writeParcelable(bean,0);
        //Parcel读写共用一个位置计数，这里一定要重置一下当前的位置
        parcel.setDataPosition(0);
        //读取Parcel
        ABean bean1 = parcel.readParcelable(ABean.class.getClassLoader());
        Log.i(TAG, "TestParce: "+bean1.getIndex()+"  "+bean1.getName());
    }

    //文件写入当前应用所在目录下
    public String getAssetsCacheFile(Context context, String fileName) {
        File cacheFile = new File(context.getFilesDir(), fileName);
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            try {
                FileOutputStream outputStream = new FileOutputStream(cacheFile);
                try {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buf)) > 0) {
                        outputStream.write(buf, 0, len);
                    }
                } finally {
                    outputStream.close();
                }
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i(TAG, "getAssetsCacheFile: " + cacheFile.getAbsolutePath());
        return cacheFile.getAbsolutePath();
    }
}

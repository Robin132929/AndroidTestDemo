package com.robin.testdemo;

import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class TestReflation {
    private static final String TAG = "TestReflation";
    @Test
    public void getReflation(){
        Class cls=null;
        try {
            cls=Class.forName("com.robin.testdemo.trflation.TestReflation");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        getFiled(cls);
        Properties pro=new Properties();
        try {
            pro.load(App.get().getAssets().open("Product.properties"));
            String Classname = pro.getProperty("ProductA");
            com.robin.testdemo.trflation.TestReflation reflation= (com.robin.testdemo.trflation.TestReflation) Class.forName(Classname).newInstance();
            Log.i(TAG, "getReflation: "+reflation.getDef());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void getFiled(Class cls) {
        try {
            Field filed= cls.getDeclaredField("q");
            Log.i(TAG, "getFiled: "+filed.getName());
            for (Field declaredField : cls.getDeclaredFields()) {
                declaredField.setAccessible(true);
                Log.i(TAG, "getdeclaredField1: "+declaredField.getName());
            }
            for (Field field : cls.getFields()) {
                field.setAccessible(true);
                Log.i(TAG, "getFiled: "+field.getName());
            }
            Log.i(TAG, "getFiled: "+cls.getSuperclass().getDeclaredField("abc").getName());

            for (TypeVariable typeParameter : cls.getTypeParameters()) {
                if (typeParameter.getGenericDeclaration() instanceof Class)

                Log.i(TAG, "getFiled: "+typeParameter.getName());

            }

            for (Class anInterface : cls.getInterfaces()) {
                Log.i(TAG, "getFiled: "+anInterface.getName());
            }



        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGenerina(){
//        ArrayList<String> [] a=new ArrayList<String>[10];
//        ArrayList<String> [] a=new ArrayList[10];
//        ArrayList<String> b=new ArrayList<>();
//        b.add("b");
//        a[0]=b;
//
//        Log.i(TAG, "TestGenerina: "+a[0].get(0));

//        List<Integer> list = new ArrayList<Integer>();
//        Map<Integer, String> map = new HashMap<Integer, String>();
//        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
//        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));

        Map<String, Integer> map = new HashMap<String, Integer>() {};

        Type type = map.getClass().getGenericSuperclass();
//        ParameterizedType parameterizedType = ParameterizedType.class.cast(type);
//        for (Type typeArgument : parameterizedType.getActualTypeArguments()) {
//            System.out.println(typeArgument.getTypeName());
//        }
        Log.i(TAG, "TestGenerina: "+map.getClass().getSuperclass()+" |||type: "+type.getTypeName());
    }
    class Bean<T extends TestReflation> { //TODO
        }
}

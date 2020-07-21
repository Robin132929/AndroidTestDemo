package com.robin.testdemo;

import android.content.Context;
import android.util.Log;
import android.view.animation.Animation;

import com.robin.testdemo.zhujie.Name;
import com.robin.testdemo.zhujie.Names;
import com.robin.testdemo.zhujie.TestZhujie;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static final String TAG = "ExampleInstrumentedTest";
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.robin.testdemo", appContext.getPackageName());
        Names animation=TestZhujie.class.getAnnotation(Names.class);
        for (Name name : animation.value()) {
            Log.i(TAG, "useAppContext: getclass "+name.getclass());
            Log.i(TAG, "useAppContext: getname "+name.getname());
        }

      TypeVariable<Class<demo3>>[] typeVariable=demo3.class.getTypeParameters();
        for (TypeVariable<Class<demo3>> typeParameter : typeVariable) {
            System.out.println("变量名称:" + typeParameter.getName());
            System.out.println("这个变量在哪声明的:" + typeParameter.getGenericDeclaration());
            Type[] bounds = typeParameter.getBounds();
            System.out.println("这个变量上边界数量:" + bounds.length);
            System.out.println("这个变量上边界清单:");
            for (Type bound : bounds) {
                System.out.println(bound.getTypeName());
            }
            System.out.println("--------------------");
        }
        Method m=null;
        for (Method method : demo3.class.getMethods()) {
            if (method.getName().equals("method1")){
                m=method;
            }
        }
        if (m!=null){
          Type[ ] types=  m.getGenericParameterTypes();
            for (Type genericParameterType : types) {
                if (genericParameterType instanceof TypeVariable){
                    TypeVariable typeVariable1= (TypeVariable) genericParameterType;
                    System.out.println("变量类型名称:" + typeVariable1.getTypeName());
                    System.out.println("变量名称:" + typeVariable1.getName());
                    System.out.println("这个变量在哪声明的:" + typeVariable1.getGenericDeclaration());
                    Type[] bounds = typeVariable1.getBounds();
                    System.out.println("这个变量上边界数量:" + bounds.length);
                    System.out.println("这个变量上边界清单:");
                    for (Type bound : bounds) {
                        System.out.println(bound.getTypeName());
                    }
                }else if (genericParameterType instanceof Class){
                   Class c = (Class) genericParameterType;
                    System.out.println("参数类型名称:" + c.getTypeName());
                    System.out.println("参数类名:" + c.getName());
                }
            }

            //获取方法的返回值，也是一个泛型变量
            System.out.println("m1方法返回值类型信息:----------");
            Type genericReturnType = m.getGenericReturnType();
            if (genericReturnType instanceof TypeVariable) {
                TypeVariable pt = (TypeVariable) genericReturnType;
                System.out.println("变量名称:" + pt.getName());
                System.out.println("这个变量在哪声明的:" + pt.getGenericDeclaration());
                Type[] bounds = pt.getBounds();
                System.out.println("这个变量上边界数量:" + bounds.length);
                System.out.println("这个变量上边界清单:");
                for (Type bound : bounds) {
                    System.out.println(bound.getTypeName());
                }
                System.out.println("--------------------");
            }

            //获取方法中声明的泛型参数列表
            System.out.println("m1方法中声明的泛型变量类型列表:----------");
            TypeVariable<Method>[] typeParameters = m.getTypeParameters();
            for (TypeVariable<Method> pt : typeParameters) {
                System.out.println("变量类型名称:" + pt.getTypeName());
                System.out.println("变量名称:" + pt.getName());
                System.out.println("这个变量在哪声明的:" + pt.getGenericDeclaration());
                Type[] bounds = pt.getBounds();
                System.out.println("这个变量上边界数量:" + bounds.length);
                System.out.println("这个变量上边界清单:");
                for (Type bound : bounds) {
                    System.out.println(bound.getTypeName());
                }
                System.out.println("--------------------");
            }
        }

    }
    @Test
    public  void getGen(){
        Demo6 demo6 = new Demo6();
        //demo6Class对应的是Demo6的Class对象
        Class<? extends Demo6> demo6Class = demo6.getClass();//@2

        //获取Demo6的父类的详细类型信息，包含泛型信息
        Type genericSuperclass = demo6Class.getGenericSuperclass(); //@3
        // 泛型类型用ParameterizedType接口表示，输出看一下是不是这个接口类型的
        System.out.println("super :"+genericSuperclass.getClass()); //@4
        if (genericSuperclass instanceof ParameterizedType) { //@5
            ParameterizedType pt = (ParameterizedType) genericSuperclass;
            System.out.println("eawType :"+pt.getRawType());
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument.getTypeName());
            }
            System.out.println("owner :"+pt.getOwnerType());
        }
    }
    interface demo1{

    }

    interface demo2{

    }

    public class demo3<T ,V extends Integer,E extends demo1&demo2>{

        public <T1 ,V1 extends Integer,E1 extends demo1&demo2> E1 method1(T1 t1,V1 v1,E1 v ,String string){
            return v;
        }
    }

    //泛型类
    class Demo<T1, T2> {//@0

    }

    public class Demo6 extends Demo<String, Integer> { //@1
    }
}

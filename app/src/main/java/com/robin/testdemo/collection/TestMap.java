package com.robin.testdemo.collection;

import android.util.ArrayMap;
import android.util.SparseArray;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {
    static Hashtable<String ,Integer> hashtable=new Hashtable<>();
    static HashMap<String ,Integer> hashMap=new HashMap<>();
    static LinkedHashMap<String ,Integer> linkedHashMap=new LinkedHashMap<>();
    static TreeMap<String ,Integer> treeMap=new TreeMap<>();
    static IdentityHashMap<String ,Integer> identityHashMap=new IdentityHashMap<>();
    static WeakHashMap<String ,Integer> weakHashMap=new WeakHashMap<>();
    static ConcurrentHashMap<String ,Integer> concurrentHashMap=new ConcurrentHashMap<>();
    static ArrayMap<String ,Integer> arrayMap=new ArrayMap<>();


    public static void TestHashMap(){
//        SparseArray
        Properties pro = new Properties() ;
    }


    public static void TestHashtable(){

    }


    public static void TestLinkedHashMap(){

    }


    public static void TestTreeMap(){

    }


    public static void TestIdentityHashMap(){

    }


    public static void TestWeakHashMap(){

    }

    public static void TestConcurrentHashMa(){

    }

    public static void TestArrayMap(){

    }

}

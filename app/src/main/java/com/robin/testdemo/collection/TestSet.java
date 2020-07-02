package com.robin.testdemo.collection;

import android.util.Log;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class TestSet {
    private static final String TAG = "TestSet";
   static HashSet<Integer> hashSet=new HashSet<>();
   static TreeSet<Integer> treeSet=new TreeSet<>();
   static LinkedHashSet<Integer> linkedHashSet=new LinkedHashSet<>();
    /*
      HashSet内部值得存取基于hashmap，插入无序元素值唯一，允许null元素。
     */
    public static void  TestHashSet(){
        hashSet.add(null);
        hashSet.add(null);
        hashSet.add(1);
        Log.i(TAG, "TestHashSet: "+hashSet.size());
        for (Integer integer : hashSet) {
            Log.i(TAG, "TestHashSet: "+integer);

        }
    }
    /*
      TreeSet内部使用treemap进行存取，所以会对插入的元素进行排序，排序是根据自然排序或者比较器排序，即在构造TreeSet时如果不指定排序规则，那么传入的元素就必须实现Comparator接口，add\remove\contains的时间复杂度为O（log(n)）
     */

    public static void TestTreeSet(){
        //treeSet未指定排序规则所以内部元素必须实现Comparator接口
        treeSet.add(1);  // OK
        treeSet.add(12);
        treeSet.add(10);
        treeSet.add(2);
        treeSet.add(22);
        for (Integer integer : treeSet) {
            Log.i(TAG, "TestTreeSet: "+integer);
        }

        Log.i(TAG, "TestTreeSet: ceil "+treeSet.ceiling(11));
        Log.i(TAG, "TestTreeSet: first "+treeSet.first());
        Log.i(TAG, "TestTreeSet: higher "+treeSet.higher(9));
        Log.i(TAG, "TestTreeSet: headSet "+treeSet.headSet(3));
        //treeSet1接收bean类型元素 bean未实现Comparator接口 则编译不通过。
//       TreeSet<bean> treeSet1=new TreeSet<>();
//       treeSet.add(bean);  //NO

//        TreeSet<bean> treeSet2=new TreeSet<>(new Comparator<bean>() {  //treeSet2接收bean类型元素，显式指定排序规则。
//            @Override
//            public int compare(bean o1, bean o2) {
//                return o1.a-o2.a;
//            }
//        });

    }

    public class bean{
        int a;
        int b;
    }

    /*
     LinkedHashSet基于双链表和哈希函数双重实现，哈希函数保证内部元素值唯一，双链表保证插入顺序。
     */
    public static void TestLinkedHashSet(){
      linkedHashSet.add(1);
      linkedHashSet.add(13);
      linkedHashSet.add(10);
      linkedHashSet.add(18);
      linkedHashSet.add(9);

        for (Integer integer : linkedHashSet) {
            Log.i(TAG, "TestLinkedHashSet: "+integer    );
        }
    }
}

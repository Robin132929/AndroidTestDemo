package com.robin.testdemo.collection;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

public class TestQueue {
    private static final String TAG = "TestQueue";
    static PriorityQueue<Integer> priorityQueue=new PriorityQueue<>();
    static ArrayDeque<Integer> arrayDeque=new ArrayDeque<>();

    /*
    PriorityQueue使用可变数组来实现堆来存取元素,元素不可为空，
     */
    public static void TestPriorityQueue(){
        priorityQueue.add(1);
        priorityQueue.add(10);
        priorityQueue.add(3);
        priorityQueue.add(5);
        priorityQueue.add(2);


        for (Integer integer : priorityQueue) {
            Log.i(TAG, "TestPriorityQueue: "+integer+" "+ priorityQueue.peek());
        }
        Log.i(TAG, "TestPriorityQueue: "+(5&5));
        Log.i(TAG, "TestPriorityQueue: "+(4&5));
        Log.i(TAG, "TestPriorityQueue: "+(3&5));
        Log.i(TAG, "TestPriorityQueue: "+(2&5));
        Log.i(TAG, "TestPriorityQueue: "+(1&5));
        Log.i(TAG, "TestPriorityQueue: "+(0&5));
    }
    /*
    ArrayDeque内部使用可变数组进行存取，数组是循环使用的 只有head==tail的时候才会扩容至原来的2倍。
    不予许null元素，作为stack比Vector效率高，作为deque效率比LinkedList高
     */

    public static void TestArrayDeque(){
        arrayDeque.addFirst(1);
        arrayDeque.addFirst(2);
        arrayDeque.addFirst(3);
        arrayDeque.addLast(4);
        arrayDeque.addLast(5);
        arrayDeque.addLast(6);

        for (Integer integer : arrayDeque) {
            Log.i(TAG, "TestArrayDeque: "+integer);

        }

    }
}

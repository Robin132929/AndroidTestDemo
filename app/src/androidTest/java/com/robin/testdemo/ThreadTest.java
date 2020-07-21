package com.robin.testdemo;

import android.content.Context;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class ThreadTest {

    private static final String TAG = "ThreadTest";

    @Test
    public void CreateThread(){
        //1、继承thread
        class MyThread extends Thread{
            @Override
            public void run() {
                super.run();
                int count =0;
                for (int i = 0; i < 100000000; i++) {
                    count+=i;
                }
                Log.i(TAG, "MyThread run: "+count);
            }
        }

        new MyThread().start();


        //2、实现runnable
         class RunThread implements Runnable{

             @Override
             public void run() {
                 int count =0;
                 for (int i = 0; i < 100000000; i++) {
                     count+=i;
                 }
                 Log.i(TAG, "RunThread run: "+count);

             }
         }
         new Thread(new RunThread()).start();


        //3、实现callable
        class CallThread implements Callable<Integer>{

            @Override
            public Integer call() throws Exception {
                int count =0;
                for (int i = 0; i < 100000000; i++) {
                    count+=i;
                }
                return count;
            }
        }

        FutureTask<Integer> fa=new FutureTask<Integer>(new CallThread());
        new Thread(fa).start();
        try {
            Log.i(TAG, "CreateThread: "+fa.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void Extor(){
        class RunThread implements Runnable{

            @Override
            public void run() {




            }
        }
        Object lock=new Object();
        Runnable runnable=new RunThread();
        Thread thread= new Thread(){
            @Override
            public void run() {
                super.run();
                Log.i(TAG, "run: ");
                synchronized (this){
                    Log.i(TAG, "RunThread run start: "+Thread.currentThread().getName());

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "RunThread run finish: "+Thread.currentThread().getName());
                    this.notify();
                }
            }
        };
        Thread thread1= new Thread(){
            @Override
            public void run() {
                super.run();
                Log.i(TAG, "run1: ");
                synchronized (thread){
                    Log.i(TAG, "RunThread1 run start: "+Thread.currentThread().getName());

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "RunThread1 run finish: "+Thread.currentThread().getName());
                    this.notify();
                }
            }
        };

        synchronized (thread){
            thread.start();
            thread1.start();
            try {
                Log.i(TAG, "Extor: "+Thread.currentThread().getName());

                thread.wait();

                Log.i(TAG, "Extor: "+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();

            }

        }
    }

    public synchronized void add(int i){
        i+=1;
        Log.i(TAG, "add: "+i);
    }

    @Test
    public void TestLock(){
        Lock lock=new ReentrantLock();
       Condition condition= lock.newCondition();
        Condition condition1= lock.newCondition();
        Thread thread= new Thread(){
            @Override
            public void run() {
                super.run();
                Log.i(TAG, "run: ");
                lock.lock();
                try {
                    Log.i(TAG, "TestLock: start "+Thread.currentThread().getName());

                    Log.i(TAG, "TestLock: finish "+Thread.currentThread().getName());

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    Log.i(TAG, "TestLock: unloack"+Thread.currentThread().getName());
                    condition.signal();
                    lock.unlock();
                }
            }
        };
        Thread thread1= new Thread(){
            @Override
            public void run() {
                super.run();
                Log.i(TAG, "run1: ");
                lock.lock();
                try {
                    Log.i(TAG, "TestLock1: start "+Thread.currentThread().getName());

//                    Thread.sleep(3000);
                    Log.i(TAG, "TestLock1: finish "+Thread.currentThread().getName());

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    Log.i(TAG, "TestLock1: unloack"+Thread.currentThread().getName());
                    condition.signal();
                    lock.unlock();
                }
            }
        };
        lock.lock();
        thread.start();
        thread1.start();

        try {
            Log.i(TAG, "TestLock: await");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            Log.i(TAG, "TestLock: unlock");
            lock.unlock();
        }
    }

}

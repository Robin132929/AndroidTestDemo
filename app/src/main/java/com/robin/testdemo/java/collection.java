package com.robin.testdemo.java;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class collection {

//         File file=new File("abc");
//    RandomAccessFile
//         public void  test(){
//             file.
//         }

    Lock lock=new ReentrantLock();

    public void lock(){
        lock.lock();
        try {

        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }

    public void trylock(){
        if (lock.tryLock()){
            try {

            }catch (Exception e){

            }finally {
                lock.unlock();
            }
        }else {
         //为获取到锁
        }

    }

    public void lockinter() throws InterruptedException {
        lock.lockInterruptibly();
        try {
     int a=0;
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void unlock(){

    }
}

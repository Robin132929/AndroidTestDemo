package com.robin.testdemo;

import android.content.Intent;

import com.robin.testdemo.collection.TestSet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;


public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int a=assertEquals(4, 2 + 2);

          final int COUNT_BITS = Integer.SIZE - 3;
        final int CAPACITY   = (1 << COUNT_BITS) - 1;

        // runState is stored in the high-order bits
        final int RUNNING    = -1 << COUNT_BITS;
        final int SHUTDOWN   =  0 << COUNT_BITS;
         final int STOP       =  1 << COUNT_BITS;
         final int TIDYING    =  2 << COUNT_BITS;
         final int TERMINATED =  3 << COUNT_BITS;
        System.out.println("test :"+(RUNNING|0));

        TestSet.TestHashSet();
    }

    private int assertEquals(int i, int i1) {
        try {
            i+=1;
            return i;
        }catch (Exception e){

        }finally {
            i+=1;
            return i;
        }

//        return i+100;
    }

    public class ListNode {
      int val;
      ListNode next;
     ListNode(int x) {
          val = x;
          next = null;
      }
  }
    public class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
   }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pa = headA;
        ListNode pb = headB;
        while (pa != pb) {
            if (pa != null) {
                pa = pa.next;
            }else{
                pa = headB;
            }
            if (pb != null) {
                pb = pb.next;
            }else{
                pb = headA;
            }
        }
        if (pa ==null) {
            return null;
        }
        return pa;

    }
    public int maxLevelSum(TreeNode root) {
        LinkedList<TreeNode> queue=new LinkedList<>();
        int  ans_row=0,row=0,max_row=0,sum=0;
        TreeNode current=root;
        TreeNode split=new TreeNode(Integer.MAX_VALUE);
        split.left=null;
        split.right=null;
        queue.add(root);
        queue.add(split);
        while (queue.size()!=0){
           TreeNode temp= queue.poll();
           System.out.println(" temp "+temp.val);
           if (temp.val==split.val){
               System.out.println(" temp is split");
               queue.add(split);
               row++;
               ans_row=max_row<row ?row:ans_row;
               max_row= Math.max(max_row, sum);
               sum=0;
           }
           sum+=temp.val;
           if (temp.left!=null){
               queue.add(temp.left);
           }else if(temp.right!=null){
               queue.add(temp.right);
           }
        }
        return ans_row;
    }

    public int[] maxDepthAfterSplit(String seq) {
        int num=0;
      int [] ans=new int[seq.length()];
        for (int i = 0; i < seq.length(); i++) {
            char c = seq.charAt(i);
            if (c == '(') {//入栈,栈内深度增加
                num++;
                ans[i] = num % 2;
            } else {//出栈，栈内深度减少
                ans[i] = num % 2;
                num--;
            }
        }

//        for (char c : seq.toCharArray()) {
//            if (c=='('){
//                num++;
//                i++;
//                ans[i]=num%2;
//            }else {
//                num--;
//                i++;
//                ans[i]=num%2;
//            }
//        }
        return ans;
    }
    public int flipgame(int[] fronts, int[] backs) {
        HashSet<Integer> same=new HashSet<>();
        int ans=9999;
        for (int i = 0; i < fronts.length; i++) {
            if (fronts[i]==backs[i]){
                same.add(fronts[i]);
            }
        }
        for (int front : fronts) {
            if (!same.contains(front)){
                ans=Math.min(ans,front);
            }
        }
        for (int back : backs) {
            if (!same.contains(back)){
                ans=Math.min(ans,back);
            }
        }

        return ans%9999;
    }

    public ListNode detectCycle(ListNode head) {
         ListNode fast=head;
         ListNode slow=head;
         int i=0;
         do {
             fast=fast.next.next;
             slow=slow.next;
         }while (fast!=slow);


         if (fast!=slow){
             return null;
         }
         fast=head;
         //fast 走x slow走y,共走i步，x-y=i;
         if (fast!=slow){
             fast=fast.next;
             slow=slow.next;
         }
         return head;

    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> res=new ArrayList<>();

        int min= Integer.MAX_VALUE;
        for (int i = 0; i < arr.length-1; i++) {
           int n= arr[i+1]-arr[i];
            if (n<=min){
                min=n;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i+1]-arr[i]==min){
                List<Integer> data=new ArrayList<>();
                data.add(arr[i]);
                data.add(arr[i+1]);
                res.add(data);
            }
        }
    return res;
    }
//2，12，20,21,22,23,24,25,26,27,28,29,32,42,52,62,72,82,92


//    public int numberOf2sInRange(int n) {
//        int count=0;
//        for (int i = 10; i <= 25; i++) {
//            int cur,num=i;
//            System.out.println("cur "+num);
//            while (num!=0){
//                cur=num%10;
//                if (cur==2){
//                    count++;
//                }
//                num=num/10;
//            }
//        }
//       if (n<10){
//           return 1;
//       }else {
//
//       }
//
//    }
@Test
public void Test(){
        Object lock=new Object();
    Thread t0 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("Thread 0 start!!!!!!");
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (Exception e) {
                }
                System.out.println("Thread 0 end!!!!!!");
            }
        }
    });
    Thread t3 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("Thread 3 start!!!!!!");
            synchronized (lock) {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                }
                lock.notify();
                System.out.println("Thread 3 end!!!!!!");
            }
        }
    });
    Thread t4 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("Thread 4 start!!!!!!");
            synchronized (lock) {
                System.out.println("Thread 4 end!!!!!!");
            }
        }
    });
    Thread t5 = new Thread(new Runnable() {
        @Override
        public void run() {
            int a = 0;
            System.out.println("Thread 5 start!!!!!!");
            synchronized (lock) {
                a++;
                System.out.println("Thread 5 end!!!!!!");
            }
        }
    });
    Thread t6 = new Thread(new Runnable() {
        @Override
        public void run() {
            int a = 0;
            System.out.println("Thread 6 start!!!!!!");
            synchronized (lock) {
                a++;
                System.out.println("Thread 6 end!!!!!!");
            }
        }
    });
    t0.start();
    try {
        TimeUnit.SECONDS.sleep(1);
    } catch (Exception e) {
    }
    t3.start();
    try {
        TimeUnit.SECONDS.sleep(1);
    } catch (Exception e) {
    }
    t4.start();
    try {
        TimeUnit.SECONDS.sleep(1);
    } catch (Exception e) {
    }
    t5.start();
    try {
        TimeUnit.SECONDS.sleep(1);
    } catch (Exception e) {
    }
    t6.start();
}
}
package com.robin.testdemo;

import com.robin.testdemo.collection.TestSet;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        int a=assertEquals(4, 2 + 2);
        System.out.println("test :"+a);

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
    
}
package interview_code;

import java.util.HashSet;

public class listnode {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    private void swap(int[] nums, int l, int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    //24 翻转链表
    public ListNode reverseList(ListNode head) {
        ListNode cur = head, pre = null;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //25 合并两个排序链表
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode dum = new ListNode(0), cur = dum;
            while(l1 != null && l2 != null) {
                if(l1.val < l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                }
                else {
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }
            cur.next = l1 != null ? l1 : l2;
            return dum.next;
        }
    }


    public static void main(String[] args) {
        int[] res = {3,4,5,1,2};
        System.out.println(Math.pow(10,2));


    }
}

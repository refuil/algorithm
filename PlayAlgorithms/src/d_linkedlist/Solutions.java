package d_linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solutions {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //19
    //只遍历一次就完成，需要用两个指针cur pre
    //首先 cur 指针先向前走N步，如果此时 cur 指向空，说明N为链表的长度，则需要移除的为首元素，那么此时返回 head->nex即可，
    //如果 cur 存在，再继续往下走，此时 pre 指针也跟着走，
    // 直到 cur 为最后一个元素时停止，此时 pre 指向要移除元素的前一个元素，
    // 再修改指针跳过需要移除的元素即可
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null) return head;
        ListNode cur = head;
        ListNode pre = head;
        for(int i=0; i<n; i++) cur = cur.next;
        if(cur ==null) return head.next;
        //cur存在,继续走
        while(cur.next != null) {
            cur = cur.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return head;
    }

    //61 旋转链表
    //输入: 1->2->3->4->5->NULL, k = 2
    //输出: 4->5->1->2->3->NULL
    //一个指针的解法：遍历得到长度n，
    // 把链表头和尾链接起来，在往后走n - k % n个节点就到达新链表的头结点前一个点，这时断开链表
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return head;
        ListNode cur = head;
        int n =1;
        while(cur.next != null) {
            cur = cur.next;
            n++;
        }
        cur.next = head;
        int m = n - k % n;
        for(int i=0; i <m; i++){
            cur = cur.next;
        }
        ListNode newhead = cur.next;
        cur.next = null;  //断开链表
        return newhead;
    }

    //82  删除排序链表中的重复元素 II
    //输入: 1->2->3->3->4->4->5
    //输出: 1->2->5  只保留没有出现的
    //定义一个前驱指针和一个现指针，每当前驱指针指向新建的节点，
    // 现指针从下一个位置开始往下遍历，遇到相同的则继续往下，
    // 直到遇到不同项时，把前驱指针的next指向下面那个不同的元素。
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        dummy.next = head;
        while(pre.next != null){
            ListNode cur = pre.next;
            while(cur.next != null && cur.next.val == cur.val){
                cur = cur.next;
            }
            //如果现指针遍历的第一个元素就不相同，则把前驱指针向下移一位。
            if(cur != pre.next) pre.next = cur.next;
            else pre = pre.next;
        }
        return dummy.next;
    }

    //递归：1. 终止条件 为空 2.返回值，已经处理好的 head.next
    //3.本级递归操作：
    public ListNode deleteDuplicates2(ListNode head) {
        if(head ==null) return head;
        if(head.next != null && head.val == head.next.val){
            while(head.next != null && head.val == head.next.val){
                head = head.next;
            }
            return deleteDuplicates2(head.next);
        }
        head.next = deleteDuplicates2(head.next);
        return head;
    }

    //86 分隔链表
    //输入: head = 1->4->3->2->5->2, x = 3
    //输出: 1->2->2->4->3->5
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        while(pre.next != null && pre.next.val < x)
            pre = pre.next;
        cur = pre;  //找到第一个大于x的4
        while(cur.next != null){
            if(cur.next.val < x){
                ListNode temp = cur.next;
                cur.next = temp.next;
                temp.next = pre.next;
                pre.next = temp;
                pre = pre.next;
            }else{
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    //92 反转链表II
    //输入: 1->2->3->4->5->NULL, m = 2, n = 4
    //输出: 1->4->3->2->5->NULL
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        //到m-1，题目中是从1开始计数的，这里只走了1步，就是结点1，用pre指向它
        for(int i=0; i< m-1; i++){
            pre = pre.next;
        }
        cur = pre.next;
        for(int i=m; i< n; i++){
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy;
    }

    //109. 有序链表转换二叉搜索树
    //[-10, -3, 0, 5, 9],
    //1.快慢指针找到中点
    //2 中点作为根节点，原链表断开，
    //3 递归
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) return null;
        //只有一个节点
        if(head.next == null) return new TreeNode(head.val);
        ListNode slow = head, fast = head, last = slow;
        while(fast.next != null && fast.next.next != null){
            last = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        last.next = null;
        TreeNode cur = new TreeNode(slow.val);
        if(head != slow) cur.left = sortedListToBST(head);
        cur.right = sortedListToBST(fast);
        return cur;
    }

    //142 环形链表II
    //输入：head = [3,2,0,-4], pos = 1
    //输出：tail connects to node index 1
    //复杂度O(n)的方法，使用两个指针slow,fast。两个指针都从表头开始走，slow每次走一步，
    // fast每次走两步，如果fast遇到null，则说明没有环，返回false；如果slow==fast，说明有环，
    // 并且此时fast超了slow一圈，返回true。
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) break;
        }
        if(fast == null || fast.next ==null) return null;
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    //148 排序链表 O(n log n)
    //先来非递归的
    //归并排序（又称混合排序）因其可以利用递归来交换数字，天然适合链表这种结构。
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head, fast = head, pre = head;
        while(fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return merge(sortList(head), sortList(slow));
    }

    public ListNode merge(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = merge(l1.next, l2);
            return l1;
        }else{
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    //143 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
    //将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
    //你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
    //分为以下三个小问题：
    //1. 使用快慢指针来找到链表的中点，并将链表从中点处断开，形成两个独立的链表。
    //2. 将第二个链翻转。
    //3. 将第二个链表的元素间隔地插入第一个链表中。

    public void reorderList(ListNode head) {
        if(head == null || head.next==null || head.next.next == null) return;
        ListNode fast =head;
        ListNode slow = head;
        while(fast.next !=null && fast.next.next !=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow.next;  //找到中点
        slow.next = null;
        ListNode last = mid, pre = null;
        while(last != null){      //第二个链翻转
            ListNode next = last.next;
            last.next = pre;
            pre = last;
            last = next;
        }
        while(head !=null && pre != null){  //第二个链表间隔插入第一个链表
            ListNode next = head.next;
            head.next = pre;
            pre = pre.next;
            head.next.next = next;
            head = next;
        }
    }

    public static void main(String[] args) {

    }


}

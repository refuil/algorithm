package interview_code;

import java.util.*;

public class dfs {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    //32-3 从上到下打印二叉树


    //33 二叉树的路径
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }
    boolean recur(int[] po, int i, int j) {
        if(i >= j) return true;
        int l = i;
        while(po[l] < po[j]) l++;
        int m = l;
        while(po[l] > po[j]) l++;
        return l == j && recur(po, i, m - 1) && recur(po, m, j - 1);
    }

    //34 复杂链表的复制   O(n) O(1)
    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        //将拷贝节点放到原节点后面，例如1->2->3这样的链表就变成了这样1->1'->2'->3->3'
        for (Node node = head, copy = null; node != null; node = node.next.next) {
            copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
        }
        //把拷贝节点的random指针安排上
        for (Node node = head; node != null; node = node.next.next) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
        }
        //分离拷贝节点和原节点，变成1->2->3和1'->2'->3'两个链表，后者就是答案
        Node newHead = head.next;
        for (Node node = head, temp = null; node != null && node.next != null;) {
            temp = node.next;
            node.next = temp.next;
            node = temp;
        }

        return newHead;
    }

    //35  二叉搜索树与双向链表
//    public Node iter(Node root){
//        Node head = null;
//        Node tail = null;
//        Stack<Node> stack = new Stack<>();
//        Node cur = root;
//        while (!stack.isEmpty() || cur != null){
//            while (cur != null){
//                stack.push(cur);
//                cur = cur.left;
//            }
//            cur = stack.pop();
//            if (head == null){
//                head = cur;
//                tail = cur;
//            }else{
//                tail.right = cur;
//                cur.left = tail;
//                tail = cur;
//            }
//            cur = cur.right;
//        }
//        // 注意要连接头尾，不然lc报错都莫名其妙
//        tail.right = head;
//        head.left = tail;
//        return head;
//    }

    //55-1. 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        int[] h = new int[1];
        return isBalanced(root, h);
    }
    public boolean isBalanced(TreeNode root, int[] height) {
        if (root == null) {
            height[0] = 0;
            return true;
        }
        int[] lh = new int[1];
        int[] rh = new int[1];
        if (isBalanced(root.left, lh) &&
                isBalanced(root.right, rh) &&
                Math.abs(lh[0]-rh[0])<=1) {
            height[0] = 1+Math.max(lh[0], rh[0]);
            return true;
        } else {
            return false;
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int left=0, right = len-1;
        while(left < right){
            // int mid = (right -left)/2;
            int sum = nums[left] + nums[right];
            if(sum == target){
                return new int[]{nums[left],nums[right]};
            }else if(target < sum){
                right--;
            }else{
                left++;
            }
        }
        return new int[]{};
    }


    public static void main(String[] args) {
        int[] res = {3,4,5,1,2};
        System.out.println((new array()).minArray(res));

        System.out.println(Math.pow(10,2));

        Queue<String> queue = new LinkedList<>();
        String str = "adc";

        str = str.substring(0, str.length() -1);
        System.out.println("abc:" + str);

        HashMap<Integer, Integer> map = new HashMap<>();


    }



}

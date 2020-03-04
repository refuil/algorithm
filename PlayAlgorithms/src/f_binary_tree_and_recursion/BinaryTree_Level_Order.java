package f_binary_tree_and_recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

import javafx.util.Pair;

/// 102. Binary Tree Level Order Traversal
/// https://leetcode.com/problems/binary-tree-level-order-traversal/description/
/// 二叉树的层序遍历, 递归实现
/// 时间复杂度: O(n), n为树的节点个数
/// 空间复杂度: O(n)
class BinaryTree_Level_Order {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    //递归版本
    ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
    public List<List<Integer>> levelOrder(TreeNode root){
        levelOrder(root, 0);
        return res;
    }


    public void levelOrder(TreeNode root, int l){
        if(root == null) return;
        if(res.size() == l) res.add(new ArrayList<Integer>());
        res.get(l).add(root.val);
        levelOrder(root.left, l+1);
        levelOrder(root.right, l+1);
    }


    //非递归
    public Queue levelOrder1(TreeNode root) {

        if (root == null) return null;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            System.out.println(temp.val);
            if (temp.left != null) q.add(temp.left);
            if (temp.right != null) q.add(temp.right);
        }
        return q;
    }

    //非递归，保存在 List<List<Integer>>
    public List<List<Integer>> levelOrder3(TreeNode root){
        if(root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 0;
        while(! q.isEmpty()){
            int n = q.size();
            res.add(new ArrayList<>());
            for(int i=0; i <n ; i++){
                TreeNode temp = q.poll();
                res.get(level).add(temp.val);
                if(temp.left != null) q.add(temp.left);
                if(temp.right != null) q.add(temp.right);
            }
            level++;
        }
        return res;
    }

    // 二叉树的层平均值
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return null;
        ArrayList<Double> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            double sum = 0;
            for(int i =0; i < n; i++){
                TreeNode temp = q.poll();
                sum += temp.val;
                if (temp.left != null) q.add(temp.left);
                if (temp.right != null) q.add(temp.right);
            }
            res.add(sum /n);
        }
        return res;
    }


}

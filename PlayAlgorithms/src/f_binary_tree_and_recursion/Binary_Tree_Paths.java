package f_binary_tree_and_recursion;

import java.util.List;
import java.util.ArrayList;

/// 257. Binary Tree Paths
/// https://leetcode.com/problems/binary-tree-paths/description/
/// 时间复杂度: O(n), n为树中的节点个数
/// 空间复杂度: O(h), h为树的高度
public class Binary_Tree_Paths {

    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<String> Binary_Tree_Paths(TreeNode root) {

        ArrayList<String> res = new ArrayList<String>();

        if(root == null)
            return res;

        if(root.left == null && root.right == null){
            res.add(Integer.toString(root.val));
            return res;
        }

        List<String> leftPaths = Binary_Tree_Paths(root.left);
        for(String s: leftPaths){
            StringBuilder sb = new StringBuilder(Integer.toString(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        List<String> rightPaths = Binary_Tree_Paths(root.right);
        for(String s: rightPaths) {
            StringBuilder sb = new StringBuilder(Integer.toString(root.val));
            sb.append("->");
            sb.append(s);
            res.add(sb.toString());
        }

        return res;
    }

    //将二叉树转换成 括号组成的字符串
    //[1,2,3,4]   => "1(2(4))(3)"
    public String tree2str(TreeNode t) {
        if(t == null) return "";
        StringBuilder res = new StringBuilder(Integer.toString(t.val));
        if(t.left == null && t.right == null) return res.toString();
        res.append("(");
        res.append(tree2str(t.left) + ")");
        if(t.right != null)
            res.append("(" + tree2str(t.right) + ")");
        return res.toString();
    }
}

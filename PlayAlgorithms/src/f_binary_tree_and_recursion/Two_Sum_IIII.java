package f_binary_tree_and_recursion;

import java.util.HashSet;

public class Two_Sum_IIII {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> st = new HashSet<>();
        return helper(root, k, st);
    }

    public boolean helper(TreeNode node, int k, HashSet<Integer> st){
        if(node == null) return false;
        if(st.contains(k - node.val)) return true;
        st.add(node.val);
        return helper(node.left, k, st) || helper(node.right, k, st);
    }

}

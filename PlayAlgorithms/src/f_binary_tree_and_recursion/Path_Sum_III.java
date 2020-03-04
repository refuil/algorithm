package f_binary_tree_and_recursion;

import java.util.ArrayList;

public class Path_Sum_III {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int pathSum(TreeNode root, int sum) {
        int res = 0;
        ArrayList<TreeNode> out = new ArrayList<>();
        helper(root, sum, 0, out, res);
        return res;
    }

    public void helper(TreeNode node, int sum, int curSum,
                       ArrayList<TreeNode> out, int res) {
        if (node == null) return;
        curSum += node.val;
        out.add(node);
        if (curSum == sum) ++res;
        int t = curSum;
        for (int i = 0; i < out.size() - 1; ++i) {
            TreeNode temp = out.get(i);
            t -= temp.val;
            if (t == sum) ++res;
        }
        helper(node.left, sum, curSum, out, res);
        helper(node.right, sum, curSum, out, res);
        out.remove(out.size()-1);
    }

}

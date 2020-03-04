package f_binary_tree_and_recursion;

public class Mirror_Binary_Tree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isMirror(TreeNode root){
        return mirror(root, root);
    }

    public boolean mirror(TreeNode t1, TreeNode t2){
        if(t1 == null  && t2 == null) return true;
        if(t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && mirror(t1.right, t2.left)
                && mirror(t1.left, t2.right);
    }


}

package f_binary_tree_and_recursion;

public class Main {
    public static void main(String[] args) {
        Binary_Tree_Paths();

        invertBinaryTree();
    }


    private static void invertBinaryTree(){
        InvertBinaryTree.TreeNode node1 = new InvertBinaryTree.TreeNode(3);
        InvertBinaryTree.TreeNode node2 = new InvertBinaryTree.TreeNode(-2);

        InvertBinaryTree.TreeNode node3 = new InvertBinaryTree.TreeNode(3);
        node3.left = node1;
        node3.right = node2;

        InvertBinaryTree.TreeNode node4 = new InvertBinaryTree.TreeNode(1);
        InvertBinaryTree.TreeNode node5 = new InvertBinaryTree.TreeNode(2);
        node5.right = node4;

        InvertBinaryTree.TreeNode node6 = new InvertBinaryTree.TreeNode(5);
        node6.left = node3;
        node6.right = node5;

        InvertBinaryTree.TreeNode node7 = new InvertBinaryTree.TreeNode(11);
        InvertBinaryTree.TreeNode node8 = new InvertBinaryTree.TreeNode(-3);
        node8.right = node7;

        InvertBinaryTree.TreeNode node9 = new InvertBinaryTree.TreeNode(10);
        node9.left = node6;
        node9.right = node8;

        InvertBinaryTree.TreeNode tree = new InvertBinaryTree().invertTree(node9);

        System.out.println(new InvertBinaryTree().binaryTreePaths(tree));
    }


    private static void Binary_Tree_Paths(){
        /*****************
         *
         *       10
         *      /  \
         *     5   -3
         *    / \    \
         *   3   2   11
         *  / \   \
         * 3  -2   1
         *****************/
        Binary_Tree_Paths.TreeNode node1 = new Binary_Tree_Paths.TreeNode(3);
        Binary_Tree_Paths.TreeNode node2 = new Binary_Tree_Paths.TreeNode(-2);

        Binary_Tree_Paths.TreeNode node3 = new Binary_Tree_Paths.TreeNode(3);
        node3.left = node1;
        node3.right = node2;

        Binary_Tree_Paths.TreeNode node4 = new Binary_Tree_Paths.TreeNode(1);
        Binary_Tree_Paths.TreeNode node5 = new Binary_Tree_Paths.TreeNode(2);
        node5.right = node4;

        Binary_Tree_Paths.TreeNode node6 = new Binary_Tree_Paths.TreeNode(5);
        node6.left = node3;
        node6.right = node5;

        Binary_Tree_Paths.TreeNode node7 = new Binary_Tree_Paths.TreeNode(11);
        Binary_Tree_Paths.TreeNode node8 = new Binary_Tree_Paths.TreeNode(-3);
        node8.right = node7;

        Binary_Tree_Paths.TreeNode node9 = new Binary_Tree_Paths.TreeNode(10);
        node9.left = node6;
        node9.right = node8;

        System.out.println((new Binary_Tree_Paths()).Binary_Tree_Paths(node9));
    }
}

package tree;

public class Main {
    public static void main(String[] args) {
        binaryTreePath();

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


    private static void binaryTreePath(){
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
        BinaryTreePath.TreeNode node1 = new BinaryTreePath.TreeNode(3);
        BinaryTreePath.TreeNode node2 = new BinaryTreePath.TreeNode(-2);

        BinaryTreePath.TreeNode node3 = new BinaryTreePath.TreeNode(3);
        node3.left = node1;
        node3.right = node2;

        BinaryTreePath.TreeNode node4 = new BinaryTreePath.TreeNode(1);
        BinaryTreePath.TreeNode node5 = new BinaryTreePath.TreeNode(2);
        node5.right = node4;

        BinaryTreePath.TreeNode node6 = new BinaryTreePath.TreeNode(5);
        node6.left = node3;
        node6.right = node5;

        BinaryTreePath.TreeNode node7 = new BinaryTreePath.TreeNode(11);
        BinaryTreePath.TreeNode node8 = new BinaryTreePath.TreeNode(-3);
        node8.right = node7;

        BinaryTreePath.TreeNode node9 = new BinaryTreePath.TreeNode(10);
        node9.left = node6;
        node9.right = node8;

        System.out.println((new BinaryTreePath()).binaryTreePaths(node9));
    }
}

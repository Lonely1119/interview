package cn.raocloud.interview.algorithm.simple;

/**
 * 124.二叉树中最大路径之后 <link>https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/</link>
 * 输入：[1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * 输出：6
 *
 * 输入：[-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 输出：42
 */
public class MaxPathSum {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(maxPathSum(root));
        root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(maxPathSum(root));
    }

    public static int maxPathSum(TreeNode root) {
        if(root == null) { return 0; }
        int left = Math.max(0, maxPathSum(root.left));
        int right = Math.max(0, maxPathSum(root.right));
        return Math.max(Math.max(left, right), left + right + root.val);
    }

    public static class TreeNode {
      public int val;
      public TreeNode left;
      public TreeNode right;
      public TreeNode(){}
      public TreeNode(int val){ this.val = val; }
      public TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}

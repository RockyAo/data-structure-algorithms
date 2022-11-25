package com.rocky.ao.tree;

/**
 * @author yun.ao
 * @date 2022/11/25 18:48
 * @description
 * https://leetcode.com/problems/balanced-binary-tree/
 * https://leetcode.cn/problems/balanced-binary-tree/
 */
public class _110_BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }
}

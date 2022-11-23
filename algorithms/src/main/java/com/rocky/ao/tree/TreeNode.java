package com.rocky.ao.tree;

/**
 * @author yun.ao
 * @date 2022/11/23 14:28
 * @description
 */

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {};
    TreeNode(int val) {
        this.val = val;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
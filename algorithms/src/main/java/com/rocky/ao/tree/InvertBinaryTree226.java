package com.rocky.ao.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yun.ao
 * @date 2022/11/23 14:28
 * @description
 */
public class InvertBinaryTree226 {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) { return root; }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return root;
    }

    public TreeNode invertTree1(TreeNode root) {
        if (root == null) { return root; }

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public TreeNode invertTree2(TreeNode root) {
        if (root == null) { return root; }

        invertTree(root.left);
        invertTree(root.right);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        return root;
    }

    public TreeNode invertTree3(TreeNode root) {
        if (root == null) { return root; }

        invertTree(root.left);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        invertTree(root.left);

        return root;
    }
}

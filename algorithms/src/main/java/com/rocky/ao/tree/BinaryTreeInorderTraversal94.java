package com.rocky.ao.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yun.ao
 * @date 2022/11/23 18:32
 * @description
 */
public class BinaryTreeInorderTraversal94 {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal2(TreeNode root) {
        inorder(root);
        return list;
    }

    private void inorder(TreeNode node) {
        if (node == null) { return; }

        inorder(node.left);
        list.add(node.val);
        inorder(node.right);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> queue = new LinkedList<>();

        TreeNode node = root;

        while (!queue.isEmpty() || node != null) {
            while (node != null) {
                queue.push(node);
                node = node.left;
            }

            node = queue.pop();
            res.add(node.val);
            node = node.right;
        }

        return res;
    }
}

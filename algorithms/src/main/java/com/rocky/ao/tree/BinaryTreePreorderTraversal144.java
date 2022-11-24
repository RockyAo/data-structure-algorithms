package com.rocky.ao.tree;

import java.util.*;

/**
 * @author yun.ao
 * @date 2022/11/23 18:23
 * @description
 */
public class BinaryTreePreorderTraversal144 {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal2(TreeNode root) {
        preorder(root);
        return list;
    }

    private void preorder(TreeNode node) {
        if (node == null) { return; }

        list.add(node.val);

        preorder(node.left);
        preorder(node.right);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode node = root;

        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            node = node.right;
        }

        return res;
    }

}

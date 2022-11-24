package com.rocky.ao.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yun.ao
 * @date 2022/11/24 11:54
 * @description
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class _145_BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
//        return postorderTraversalRecursion(root, new ArrayList<>());

        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode prev = null;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();

            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }

        return res;
    }

    private List<Integer> postorderTraversalRecursion(TreeNode root, List<Integer> res) {
        if (root == null || res == null) {
            return new ArrayList<>();
        }

        postorderTraversalRecursion(root.left, res);
        postorderTraversalRecursion(root.right, res);

        res.add(root.val);
        return res;
    }
}

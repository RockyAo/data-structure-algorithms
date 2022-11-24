package com.rocky.ao.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yun.ao
 * @date 2022/11/24 11:54
 * @description
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class BinaryTreePostorderTraversal145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        return postorderTraversalRecursion(root, new ArrayList<>());
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

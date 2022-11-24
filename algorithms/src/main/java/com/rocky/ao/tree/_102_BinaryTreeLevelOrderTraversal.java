package com.rocky.ao.tree;

import java.util.*;

/**
 * @author yun.ao
 * @date 2022/11/24 14:40
 * @description
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 */
public class BinaryTreeLevelOrderTraversal102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) { return res; }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int levelSize = queue.size();

            while (levelSize != 0) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
                levelSize--;
            }

            res.add(level);
        }

        return res;
    }
}

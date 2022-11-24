package com.rocky.ao.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yun.ao
 * @date 2022/11/24 16:04
 * @description
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
 * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 */
public class BinaryTreeLevelOrderTraversalII107 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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

        List<List<Integer>> temp = new ArrayList<>();

        for (int i = res.size() - 1; i >= 0; i--) {
            temp.add(res.get(i));
        }

        return temp;
    }
}

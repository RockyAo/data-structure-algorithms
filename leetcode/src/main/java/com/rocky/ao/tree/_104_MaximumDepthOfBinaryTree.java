package com.rocky.ao.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yun.ao
 * @date 2022/11/24 15:33
 * @description
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
public class _104_MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) { return 0;}

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int levelSize = 1;
        int height = 0;

        while (!queue.isEmpty()) {
            levelSize --;

            TreeNode node = queue.poll();

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) {
                levelSize = queue.size();
                height += 1;
            }
        }

        return height;
    }
}

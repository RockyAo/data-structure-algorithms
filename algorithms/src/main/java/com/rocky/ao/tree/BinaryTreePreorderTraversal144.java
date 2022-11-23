package com.rocky.ao.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yun.ao
 * @date 2022/11/23 18:23
 * @description
 */
public class BinaryTreePreorderTraversal144 {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        preorder(root);
        return list;
    }

    private void preorder(TreeNode node) {
        if (node == null) { return; }

        list.add(node.val);

        preorder(node.left);
        preorder(node.right);
    }
}

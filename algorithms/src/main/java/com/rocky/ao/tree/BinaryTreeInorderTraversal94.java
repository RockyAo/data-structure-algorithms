package com.rocky.ao.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yun.ao
 * @date 2022/11/23 18:32
 * @description
 */
public class BinaryTreeInorderTraversal94 {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inorder(root);
        return list;
    }

    private void inorder(TreeNode node) {
        if (node == null) { return; }

        inorder(node.left);
        list.add(node.val);
        inorder(node.right);
    }
}

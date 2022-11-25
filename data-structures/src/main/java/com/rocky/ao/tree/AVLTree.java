package com.rocky.ao.tree;

import java.util.Comparator;

/**
 * @author yun.ao
 * @date 2022/11/24 16:55
 * @description
 */
public class AVLTree<E> extends BinarySearchTree<E> {
    AVLTree() {
        this(null);
    }

    AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                restoreBalance(node);
            }
        }
    }

    /**
     * jude node is balanced
     * if |balanceFactor| <= 1 then it is balanced
     * @param node node
     * @return true/false
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>)node).updateHeight();
    }

    /**
     * restore balance
     * @param grandNode unbalance node at min height
     */
    private void restoreBalance(Node<E> grandNode) {
        AVLNode<E> avlGrandNode = (AVLNode<E>) grandNode;
        AVLNode<E> parent = avlGrandNode.tallerNode();
        AVLNode<E> node = parent.tallerNode();

        if (parent.isLeftNode()) {
            if (node.isLeftNode()) {
                // LL
                rotateRight(grandNode);
            } else {
                // LR
                rotateLeft(parent);
                rotateRight(grandNode);
            }
        } else {
            if (node.isLeftNode()) {
                // RL
                rotateRight(parent);
                rotateLeft(grandNode);
            } else {
                // RR
                rotateRight(grandNode);
            }
        }
    }

    private void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.left;

        grand.right = parent.left;
        parent.left = grand;

        // 让parent成为子树根节点
        parent.parent = grand.parent;

        if (grand.isLeftNode()) {
            grand.parent.left = parent;
        } else if (grand.isRightNode()) {
            grand.parent.right = parent;
        } else {
            // root node
            root = parent;
        }

        // 更新child 的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand 的parent
        grand.parent = parent;

        updateHeight(grand);
        updateHeight(parent);
    }
    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        grand.right = parent.left;
        parent.right = grand;
    }
}

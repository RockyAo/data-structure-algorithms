package com.rocky.ao.tree;

/**
 * @author yun.ao
 * @date 2022/11/24 17:43
 * @description
 */
public class AVLNode<E> extends Node<E> {
    int height = 1;

    public AVLNode(E element, Node<E> parent) {
        super(element, parent);
    }

    public int balanceFactor() {
        int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
        int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
        return leftHeight - rightHeight;
    }

    public void updateHeight() {
        int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
        int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
        height = 1 + Math.max(leftHeight, rightHeight);
    }

    public AVLNode<E> tallerNode() {
        int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
        int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;

        if (leftHeight > rightHeight) {
            return (AVLNode<E>) left;
        } else if (leftHeight < rightHeight) {
            return (AVLNode<E>) right;
        } else {
            return (AVLNode<E>) (isLeftNode() ? left : right);
        }
    }
}
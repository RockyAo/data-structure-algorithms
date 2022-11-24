package com.rocky.ao.tree;

/**
 * @author yun.ao
 * @date 2022/11/22 16:30
 * @description
 */
public class Node<E> {
    E element;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E element, Node<E> left, Node<E> right, Node<E> parent) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public Node(E element, Node<E> parent) {
        this(element, null, null, parent);
    }

    public Node(E element) {
        this(element, null, null, null);
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean hasTwoChildren() {
        return left != null && right != null;
    }

    public static class AVLNode<E> extends Node<E> {
        int height;

        public int balanceFactor() {
            int leftHeight = left == null ? 0 : ((AVLNode<E>)left).height;
            int rightHeight = right == null ? 0 : ((AVLNode<E>)right).height;
            return leftHeight - rightHeight;
        }

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}

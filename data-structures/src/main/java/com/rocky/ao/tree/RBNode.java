package com.rocky.ao.tree;

/**
 * @author yun.ao
 * @date 2022/11/28 17:45
 * @description
 */
public class RBNode<E> extends Node<E> {
    public static enum Color {
        RED, BLACK
    }

    public  Color color;

    public RBNode(E element, Node<E> left, Node<E> right, Node<E> parent) {
        super(element, left, right, parent);
    }

    public RBNode(E element, Node<E> parent) {
        super(element, parent);
    }

    public RBNode(E element) {
        super(element);
    }

    // sibling node
    public Node<E> sibling() {
        if (isLeftNode()) {
            return parent.right;
        }

        if (isRightNode()) {
            return parent.left;
        }

        return null;
    }
}

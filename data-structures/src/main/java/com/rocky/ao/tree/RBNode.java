package com.rocky.ao.tree;

/**
 * @author yun.ao
 * @date 2022/11/28 17:45
 * @description
 */
public class RBNode<E> extends Node<E> {
    public  NodeColor color = NodeColor.RED;

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

    @Override
    public String toString() {
        return "{" +
                "color=" + color +
                " element=" + element +
                '}';
    }
}

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
}

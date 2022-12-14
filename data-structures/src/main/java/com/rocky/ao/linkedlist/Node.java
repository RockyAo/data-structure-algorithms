package com.rocky.ao.linkedlist;

/**
 * @author yun.ao
 * @date 2022/11/10 14:58
 * @description
 */
public class Node<E> {
    public E element;
    public Node<E> next;

    public Node(E element, Node<E> next) {
        this.element = element;
        this.next = next;
    }
}

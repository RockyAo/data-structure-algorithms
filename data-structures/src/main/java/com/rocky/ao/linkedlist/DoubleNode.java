package com.rocky.ao.linkedlist;

/**
 * @author yun.ao
 * @date 2022/11/17 15:04
 * @description
 */
public class DoubleNode<E> {
    public E element;
    public DoubleNode<E> prev;
    public DoubleNode<E> next;

    public DoubleNode(E element, DoubleNode<E> prev, DoubleNode<E> next) {
        this.element = element;
        this.prev = prev;
        this.next = next;
    }
}

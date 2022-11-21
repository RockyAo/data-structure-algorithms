package com.rocky.ao.queue;

import java.util.LinkedList;

/**
 * @author yun.ao
 * @date 2022/11/21 19:36
 * @description
 */
public class Queue<E> {
    private LinkedList<E> elements = new LinkedList<>();

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void enqueue(E element) {
        elements.add(element);
    }

    public E dequeue() {
        return elements.removeFirst();
    }

    public E peek() {
        return elements.getFirst();
    }

    public void clear() {
        elements.clear();
    }
}

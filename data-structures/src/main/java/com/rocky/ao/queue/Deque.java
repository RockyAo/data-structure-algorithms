package com.rocky.ao.queue;

import java.util.LinkedList;

/**
 * @author yun.ao
 * @date 2022/11/21 20:15
 * @description 双端队列
 */
public class Deque<E> {
    private LinkedList<E> elements = new LinkedList<>();

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void enqueueFront(E element) {
        elements.add(element);
    }
    public void enqueueRear(E element) {
        elements.addFirst(element);
    }

    public E dequeueFront() {
        return elements.removeFirst();
    }
    public E dequeueRear() {
        return elements.removeLast();
    }

    public E front() {
        return elements.getFirst();
    }
    public E rear() {
        return elements.getLast();
    }


    public void clear() {
        elements.clear();
    }
}
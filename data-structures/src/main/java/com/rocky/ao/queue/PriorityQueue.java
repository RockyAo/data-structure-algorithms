package com.rocky.ao.queue;

import com.rocky.ao.queue.Queue;

/**
 * @author yun.ao
 * @date 2022/12/6 11:40
 * @description
 */
public class PriorityQueue<E> {


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

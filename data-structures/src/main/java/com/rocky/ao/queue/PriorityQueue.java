package com.rocky.ao.queue;

import com.rocky.ao.heap.BinaryHeap;
import com.rocky.ao.queue.Queue;

import java.util.Comparator;

/**
 * @author yun.ao
 * @date 2022/12/6 11:40
 * @description 优先级队列
 */
public class PriorityQueue<E> {
    // 也可直接用数组实现（原理还是用数组实现二叉堆, 系统实现使用的最小堆）
    private BinaryHeap<E> heap;

    public PriorityQueue(Comparator<E> comparator) {
        heap = new BinaryHeap<>(comparator);
    }

    public PriorityQueue() {
        this(null);
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public void clear() {
        heap.clear();
    }

    public void enQueue(E element) {
        heap.add(element);
    }

    public E deQueue() {
        return heap.remove();
    }

    public E front() {
        return heap.get();
    }
}

package com.rocky.ao.heap;

import com.rocky.utils.printer.BinaryTreeInfo;
import com.rocky.utils.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author yun.ao
 * @date 2022/12/5 18:59
 * @description
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(90);

        heap.remove();
        BinaryTrees.println(heap);
    }

    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap(E[] elements, Comparator<E> comparator)  {
        super(comparator);

        if (elements == null || elements.length == 0) {
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < elements.length; i++) {
                this.elements[i] = elements[i];
            }
            heapify();
        }
    }

    public BinaryHeap(E[] elements)  {
        this(elements, null);
    }

    public BinaryHeap(Comparator<E> comparator) {
        this(null, comparator);
    }

    public BinaryHeap() {
        this(null, null);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public void add(E element) {
        elementNotNullCheck(element);
        ensureCapacity(size + 1);
        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();
        int lastIndex = --size;
        E root = elements[0];
        elements[0] = elements[lastIndex];
        elements[lastIndex] = null;
        size--;
        siftDown(0);
        return root;
    }

    @Override
    public E replace(E element) {
        // ??????????????? ????????????
        elementNotNullCheck(element);

        E root = null;
        if (size == 0) {
            elements[0] = element;
            size++;
        } else {
            root = elements[0];
            elements[0] = element;
            siftDown(0);
        }
        return root;
    }

    private void siftDown(int index) {
        E element = elements[index];
        int half = size >> 1;
        // ?????????????????????????????? == ????????????????????????
        // index < ??????????????????????????????
        // ????????????index????????????????????????
        while (index < half) {
            // index????????????2?????????
            // 1.??????????????????
            // 2.????????????????????????

            // ???????????????????????????????????????
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];

            // ????????????
            int rightIndex = childIndex + 1;

            // ????????????????????????????????????
            if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
                child = elements[childIndex = rightIndex];
            }

            if (compare(element, child) >= 0) break;

            // ?????????????????????index??????
            elements[index] = child;
            // ????????????index
            index = childIndex;
        }
        elements[index] = element;
    }

    private void siftUp(int index) {
        E element = elements[index];
        while (index > 0) {
            int parentIndex = (index - 1) >> 1;
            E parent = elements[parentIndex];
            if (compare(element, parent) <= 0) break;

            // ?????????????????????index??????
            elements[index] = parent;

            // ????????????index
            index = parentIndex;
        }
        elements[index] = element;
    }

    private void heapify() {
        // ?????????????????????
//		for (int i = 1; i < size; i++) {
//			siftUp(i);
//		}

        // ?????????????????????
        for (int i = (size >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        // ????????????????????????1.5???
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is empty");
        }
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int index = ((int)node << 1) + 1;
        return index >= size ? null : index;
    }

    @Override
    public Object right(Object node) {
        int index = ((int)node << 1) + 2;
        return index >= size ? null : index;
    }

    @Override
    public Object string(Object node) {
        return elements[(int)node];
    }
}

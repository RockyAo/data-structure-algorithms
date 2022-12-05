package com.rocky.ao.heap;

/**
 * @author yun.ao
 * @date 2022/12/5 18:54
 * @description
 */
public interface Heap<E> {
    int size();
    boolean isEmpty();
    void clear();
    void add(E element);
    E get();
    E remove();
    E replace(E element);
}

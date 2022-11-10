package com.rocky.ao.linkedlist;

import com.rocky.ao.base.AbstractList;
import com.rocky.ao.protocols.Collection;

/**
 * @author yun.ao
 * @date 2022/11/10 14:54
 * @description
 */
public class LinkedList<E> extends AbstractList<E> {
    private Node<E> first;

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }
}

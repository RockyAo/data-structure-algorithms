package com.rocky.ao.base;

import com.rocky.ao.protocols.Collection;

/**
 * @author yun.ao
 * @date 2022/11/10 16:09
 * @description
 */
public abstract class AbstractList<E> implements Collection<E> {
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }


    @Override
    public void add(E element) {
        add(size, element);
    }

    protected void raiseOutOfBoundsException(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
    }

    protected void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            raiseOutOfBoundsException(index);
        }
    }

    protected void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            raiseOutOfBoundsException(index);
        }
    }
}

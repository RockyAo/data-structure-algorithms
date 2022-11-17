package com.rocky.ao.linkedlist;

import com.rocky.ao.base.AbstractList;
import com.rocky.ao.protocols.Collection;

/**
 * @author yun.ao
 * @date 2022/11/17 15:04
 * @description
 */
public class DoubleLinkedList<E> extends AbstractList<E> {
    private DoubleNode<E> first;
    private DoubleNode<E> last;

    @Override
    public E get(int index) {
        return nodeAt(index).element;
    }

    @Override
    public E set(int index, E element) {
        DoubleNode<E> node = nodeAt(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) {
            DoubleNode<E> oldLast = last;
            last = new DoubleNode<>(element, last, null);
            if (oldLast == null) {
                // if last node is null, that means the linked list is an empty list.
                // try to add first elements
                first = last;
            } else {
                oldLast.next = last;
            }
        } else {
            DoubleNode<E> next = nodeAt(index);
            DoubleNode<E> prev = next.prev;
            DoubleNode<E> elementNode = new DoubleNode<>(element, prev, next);
            next.prev = elementNode;
            if (prev == null) {
                first = elementNode;
            } else {
                prev.next = elementNode;
            }
        }
        size ++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        DoubleNode<E> node = this.first;

        if (index == 0) {
            first = first.next;
        } else {

            DoubleNode<E> prev = nodeAt(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size --;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            DoubleNode<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }

                node = node.next;
            }
        } else {
            DoubleNode<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) {
                    return i;
                }

                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * 获取index 对应的node
     * @param index 下标
     * @return node
     */
    private DoubleNode<E> nodeAt(int index) {
        rangeCheck(index);

        DoubleNode<E> node = last;

        if (index < size >> 1) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }

        for (int i = size - 1; i > index; i--) {
            node = node.prev;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        DoubleNode<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                string.append(", ");
            }

            string.append(node);

            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}


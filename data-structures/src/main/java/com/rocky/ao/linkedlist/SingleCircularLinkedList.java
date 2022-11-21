package com.rocky.ao.linkedlist;

import com.rocky.ao.base.AbstractList;

/**
 * @author yun.ao
 * @date 2022/11/21 11:07
 * @description 单项、、
 */
public class SingleCircularLinkedList<E> extends AbstractList<E> {
    private Node<E> first;

    @Override
    public E get(int index) {
        return nodeAt(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = nodeAt(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            // if the list is empty first node is end, otherwise, get the last node and then let
            // the last node's next linked to the first.
            Node<E> lastNode = size == 0 ? first : nodeAt(size - 1);
            first = new Node<>(element, first);

            lastNode.next = first;
        } else {

            Node<E> prev = nodeAt(index - 1);
            prev.next = new Node<>(element, prev.next);
        }
        size ++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);

        Node<E> node = this.first;

        if (index == 0) {
            // get the last node
            Node<E> lastNode = nodeAt(size - 1);

            first = first.next;
            // let the end node's next link to new first node
            lastNode.next = first;

        } else {
            Node<E> prev = nodeAt(index - 1);
            node = prev.next;
            prev.next = node.next;
        }
        size --;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                }

                node = node.next;
            }
        } else {
            Node<E> node = first;
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
    }

    /**
     * 获取index 对应的node
     * @param index 下标
     * @return node
     */
    private Node<E> nodeAt(int index) {
        rangeCheck(index);

        Node<E> node = first;

        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(", [");
        Node<E> node = first;
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
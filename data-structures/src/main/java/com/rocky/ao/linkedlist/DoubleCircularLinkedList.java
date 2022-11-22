package com.rocky.ao.linkedlist;

import com.rocky.ao.base.AbstractList;

/**
 * @author yun.ao
 * @date 2022/11/21 11:31
 * @description
 */
public class DoubleCircularLinkedList<E> extends AbstractList<E> {
    private DoubleNode<E> first;
    private DoubleNode<E> last;
    private DoubleNode<E> current;

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
            last = new DoubleNode<>(element, last, first);
            if (oldLast == null) {
                // if last node is null, that means the linked list is an empty list.
                // try to add first elements
                first = last;
                first.next = first;
                first.prev = first;
            } else {
                oldLast.next = last;
                first.prev = last;
            }
        } else {
            DoubleNode<E> next = nodeAt(index);
            DoubleNode<E> prev = next.prev;
            DoubleNode<E> elementNode = new DoubleNode<>(element, prev, next);
            next.prev = elementNode;
            prev.next = elementNode;

            if (next == first) {
                first = elementNode;
            }
        }
        size ++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(nodeAt(index));
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

    // functions use to deal with Josephus problems.
    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) {
            return null;
        }
        current = current.next;
        return current.element;
    }

    public E remove() {
        if (current == null) {
            return null;
        }

        DoubleNode<E> next = current.next;
        E removeElement = remove(current);
        current = size == 0 ? null : next;
        return removeElement;
    }

    private E remove(DoubleNode<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            DoubleNode<E> prev = node.prev;
            DoubleNode<E> next = node.next;

            if (node == first) {
                // index == 0
                first = next;
            }

            if (node == last) {
                // index == size - 1
                last = prev;
            }
        }

        size --;
        return node.element;
    }


    static void josephus(int count) {
        DoubleCircularLinkedList<Integer> list = new DoubleCircularLinkedList<>();
        for (int i = 0; i < 8; i++) {
            list.add(i);
        }

        list.reset();

        while (!list.isEmpty()) {
            while (count-- > 0) {
                list.next();
            }
            list.remove();
        }
    }
}


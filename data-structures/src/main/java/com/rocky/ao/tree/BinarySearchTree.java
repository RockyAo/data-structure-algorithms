package com.rocky.ao.tree;

import java.util.Comparator;

/**
 * @author yun.ao
 * @date 2022/11/22 16:24
 * @description 二叉搜索树
 */
public class BinarySearchTree<E> {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {}

    public void add(E element) {
        elementNotNullCheck(element);

        if (root == null) {
            // add first node
            root = new Node<>(element, null);
            size ++;
            return;
        }

        // not first node

        // find the parent node
        Node<E> node = root;
        Node<E> parentNode = root;
        ComparedResult comparedResult = null;
        while (node != null) {
            comparedResult = compare(element, parentNode.element);
            parentNode = node;
            switch (comparedResult) {
                case EQUAL:
                    return;
                case ASCENDING:
                    node = node.left;
                    break;
                case DESCENDING:
                    node = node.right;
                    break;
            }
        }

        // add new node
        Node<E> newNode = new Node<>(element, parentNode);
        if (comparedResult == ComparedResult.ASCENDING) {
            parentNode.right = newNode;
        } else if (comparedResult == ComparedResult.DESCENDING) {
            parentNode.left = newNode;
        }
        size++;
    }

    public void remove(E element) {}

    public boolean contains(E element) {
        return false;
    }

    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null");
        }
    }

    private ComparedResult compare(E e1, E e2) {
        int result;
        if (comparator != null) {
            result = comparator.compare(e1, e2);
        } else {
            result = ((Comparable<E>) e1).compareTo(e2);
        }

        if (result > 0) {
            return ComparedResult.DESCENDING;
        } else if (result < 0) {
            return ComparedResult.ASCENDING;
        } else {
            return ComparedResult.EQUAL;
        }
    }
}

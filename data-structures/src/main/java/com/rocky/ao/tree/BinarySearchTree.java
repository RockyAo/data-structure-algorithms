package com.rocky.ao.tree;

import com.rocky.utils.printer.BinaryTreeInfo;
import com.rocky.utils.printer.BinaryTrees;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yun.ao
 * @date 2022/11/22 16:24
 * @description 二叉搜索树
 */
public class BinarySearchTree<E> extends BinaryTree<E> {

    public static void main(String[] args) {
        Integer[] integers = { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for (int value: integers) {
            bst.add(value);
        }

        BinaryTrees.print(bst);
//        bst.preorderTraversal();
//
//        bst.inorderTraversal();
//        bst.postorderTraversal();
        bst.levelOrderTraversal(new Visitor<Integer>() {
            @Override
            boolean visit(Integer element) {
                System.out.print(element + ",");

                return element == 2 ? true : false;
            }
        });
    }



    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

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
                    node.element = element;
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
            parentNode.left = newNode;
        } else if (comparedResult == ComparedResult.DESCENDING) {
            parentNode.right = newNode;
        }

        size++;
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (node == null) { return; }

        if (node.hasTwoChildren()) { //度为2的节点
            Node<E> successor = successor(node);
            node.element = successor.element;
            node = successor;
        }

        Node<E> replacement = node.left != null ? node.left : node.right;

        if (replacement != null) {
            // node 是度为1
            replacement.parent = node.parent;

            if (node.parent == null) {
                root = replacement;
            }

            if (node == node.parent.left) {
                node.parent.left = replacement;
            } else if (node == node.parent.right) {
                node.parent.right = replacement;
            }

        } else if (node.parent == null) {
            // 叶子且为根节点
            root = null;
        } else {
            // 叶子
            if (node == node.parent.right) {
                node.parent.right = null;
            } else {
                node.parent.left = null;
            }
        }

        size--;
    }

    private Node<E> node(E element) {
        Node<E> node = root;

        while (node != null) {
            ComparedResult comparedResult = compare(element, node.element);

            switch (comparedResult) {
                case EQUAL:
                    return node;
                case ASCENDING:
                    node = node.right;
                case DESCENDING:
                    node = node.left;
            }
        }
        return null;
    }

    public boolean contains(E element) {
        return node(element) != null;
    }

    /**
     * check element exist
     * @param element element
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element can not be null");
        }
    }

    /**
     * compare two values
     * @param e1 value1
     * @param e2 value2
     * @return ComparedResult
     */
    private ComparedResult compare(E e1, E e2) {
        int result;
        if (comparator != null) {
            // if had comparator use comparator to compare values
            result = comparator.compare(e1, e2);
        } else {
            // otherwise, use Comparable interface to compare values
            result = ((Comparable<E>) e1).compareTo(e2);
        }

        // generate compare result
        if (result > 0) {
            return ComparedResult.DESCENDING;
        } else if (result < 0) {
            return ComparedResult.ASCENDING;
        } else {
            return ComparedResult.EQUAL;
        }
    }
}

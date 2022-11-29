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
        Integer[] integers = { 39, 64, 22, 87, 2, 27, 45 };

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

    // O(logN)
    // 从小到大添加 O(n）二叉搜索树退化成链表
    public void add(E element) {
        elementNotNullCheck(element);

        if (root == null) {
            // add first node
            root = createNode(element, null);
            size ++;

            afterAdd(root);
            return;
        }

        // not first node

        // find the parent node
        Node<E> node = root;
        Node<E> parentNode = root;
        int cmp = 0;
        while (node != null) {
            cmp = compare(element, node.element);
            parentNode = node;
            if (cmp > 0 ) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }

        // add new node
        Node<E> newNode = createNode(element, parentNode);
        if (cmp > 0) {
            parentNode.right = newNode;
        } else {
            parentNode.left = newNode;
        }

        size++;

        afterAdd(newNode);
    }

    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    protected void afterAdd(Node<E> node) {}

    protected void afterRemove(Node<E> node, Node<E> replacementNode) {}

    public void remove(E element) { remove(node(element)); }

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

            afterRemove(node, replacement);
        } else if (node.parent == null) {
            // 叶子且为根节点
            root = null;
            afterRemove(node, null);
        } else {
            // 叶子
            if (node == node.parent.right) {
                node.parent.right = null;
            } else {
                node.parent.left = null;
            }
            afterRemove(node, null);
        }

        size--;
    }

    private Node<E> node(E element) {
        Node<E> node = root;

        while (node != null) {
            int cmp = compare(element, node.element);

            if (cmp == 0) { return node; }
            if (cmp > 0) {
                node = node.right;
            } else {
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
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>)e1).compareTo(e2);
    }
}

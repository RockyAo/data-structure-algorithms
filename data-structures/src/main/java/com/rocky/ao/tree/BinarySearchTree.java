package com.rocky.ao.tree;

import com.rocky.utils.printer.BinaryTreeInfo;
import com.rocky.utils.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author yun.ao
 * @date 2022/11/22 16:24
 * @description 二叉搜索树
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {
    public static void main(String[] args) {
        Integer[] integers = { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        for (int value: integers) {
            bst.add(value);
        }

        BinaryTrees.print(bst);


        bst.preorderTraversal();

        bst.inorderTraversal();
        bst.postorderTraversal();
    }


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

    public void remove(E element) {}

    public boolean contains(E element) {
        return false;
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

    // traversal
    // preorder traversal

    public void preorderTraversal() {
        System.out.println("start ------- preorder traversal");
        preorderTraversal(root);
        System.out.println("end ------- preorder traversal");
    }

    private void preorderTraversal(Node<E> node) {
        if (node == null) { return; }
        System.out.println(" " + node.element);

        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }

    // inorder traversal
    public void inorderTraversal() {
        System.out.println("start ------- inorder traversal");
        inorderTraversal(root);
        System.out.println("end ------- inorder traversal");
    }

    private void inorderTraversal(Node<E> node) {
        if (node == null) { return; }

        inorderTraversal(node.left);
        System.out.println(node.element);
        inorderTraversal(node.right);
    }

    // postorder traversal
    public void postorderTraversal() {
        System.out.println("start ------- postorder traversal");
        postorderTraversal(root);
        System.out.println("end ------- postorder traversal");
    }

    private void postorderTraversal(Node<E> node) {
        if (node == null) { return; }

        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.println(node.element);
    }

    // BinaryTreeInfo interface , use to print tree structure
    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).element;
    }
}
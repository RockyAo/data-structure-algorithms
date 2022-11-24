package com.rocky.ao.tree;

import java.util.Comparator;

/**
 * @author yun.ao
 * @date 2022/11/24 16:55
 * @description
 */
public class AVLTree<E> extends BinarySearchTree<E> {
    AVLTree() {
        this(null);
    }

    AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node.AVLNode<>(element, parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {

        }
    }

    /**
     * jude node is balanced
     * if |balanceFactor| <= 1 then it is balanced
     * @param node node
     * @return true/false
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((Node.AVLNode<E>)node).balanceFactor()) <= 1;
    }
}

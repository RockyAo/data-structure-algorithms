package com.rocky.ao.tree;

import java.util.Comparator;

/**
 * @author yun.ao
 * @date 2022/11/28 17:44
 * @description Red Black Tree
 */
public class RBTree<E> extends BinarySearchTree<E> {
    RBTree() {
        this(null);
    }

    RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        if (node == null) {
            // root node color to black
            colorNodeToBlack(node);
            return;
        }

        RBNode<E> parent = (RBNode<E>) node.parent;

        if (isBlackNode(parent)) { return; }

        Node<E> sibling = parent.sibling();
        Node<E> grand = parent.parent;

        if (isRedNode(sibling)) {
            colorNodeToBlack(parent);
            colorNodeToBlack(sibling);

            // let grand node as a new node add it again
            colorNodeToRed(grand);
            afterAdd(grand);
            return;
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        super.afterRemove(node);
    }

    /**
     * set color to node
     * @param node node
     * @param color Color Enum, Red or Black
     * @return colored node
     */
    private Node<E> color(Node<E> node, RBNode.Color color) {
        if (node == null) { return node; }

        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> colorNodeToRed(Node<E> node) {
        color(node, RBNode.Color.RED);
        return node;
    }

    private Node<E> colorNodeToBlack(Node<E> node) {
        color(node, RBNode.Color.BLACK);
        return node;
    }

    private RBNode.Color colorOf(Node<E> node) {
        return node == null ? RBNode.Color.BLACK :  ((RBNode<E>) node).color;
    }

    private boolean isBlackNode(Node<E> node) {
        return ((RBNode<E>) node).color == RBNode.Color.BLACK;
    }

    private boolean isRedNode(Node<E> node) {
        return ((RBNode<E>) node).color == RBNode.Color.RED;
    }
}

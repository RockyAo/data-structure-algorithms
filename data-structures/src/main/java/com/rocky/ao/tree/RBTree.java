package com.rocky.ao.tree;

import com.rocky.utils.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author yun.ao
 * @date 2022/11/28 17:44
 * @description Red Black Tree
 */
public class RBTree<E> extends BalanceBinarySearchTree<E> {
    public static void main(String[] args) {
        Integer[] integers = { 59, 12, 65, 68, 42, 73, 92, 74, 71, 1 };

        RBTree<Integer> avl = new RBTree<>();

        for (int value: integers) {
            avl.add(value);
            System.out.println("add: -->" + value);
            BinaryTrees.println(avl);
        }

        BinaryTrees.print(avl);
    }


    RBTree() {
        this(null);
    }

    RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        RBNode<E> parent = (RBNode<E>) node.parent;

        if (parent == null) {
            // root node color to black
            colorNodeToBlack(node);
            return;
        }

        if (isBlackNode(parent)) { return; }

        Node<E> sibling = parent.sibling();
        Node<E> grand = colorNodeToRed(parent.parent);

        if (isRedNode(sibling)) {
            colorNodeToBlack(parent);
            colorNodeToBlack(sibling);

            // let grand node as a new node add it again
            afterAdd(grand);
            return;
        }

        if (parent.isLeftNode()) {
            // L
            if (node.isLeftNode()) {
                // LL
                colorNodeToBlack(parent);
            } else {
                // LR
                colorNodeToBlack(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            // R
            if (node.isLeftNode()) {
                // RL
                colorNodeToBlack(node);

                rotateRight(parent);
            } else {
                // RR
                colorNodeToBlack(parent);
            }
            rotateLeft(grand);
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
        return node == null ? RBNode.Color.BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlackNode(Node<E> node) {
        return colorOf(node) == RBNode.Color.BLACK;
    }

    private boolean isRedNode(Node<E> node) {
        return colorOf(node) == RBNode.Color.RED;
    }
}

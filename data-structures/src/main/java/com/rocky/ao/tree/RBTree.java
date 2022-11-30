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


    public RBTree() {
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
        if (isRedNode(node)) {
            // color to black
            colorNodeToBlack(node);
            return;
        }

        // delete black node
        Node<E> parent = node.parent;

        if (parent == null) { return; }

        // get delete node is left or right
        boolean left = parent.left == null;

        Node<E> sibling = left ? parent.right : parent.left;

        if (left) {
            if (isRedNode(sibling)) {
                colorNodeToBlack(sibling);
                colorNodeToRed(parent);
                rotateLeft(parent);

                sibling = parent.left;
            }

            // sibling node must be black
            if (isBlackNode(sibling.left) && isBlackNode(sibling.right)) {
                // sibling doesn't have any red node, parent node combine with sibling node
                boolean isParentBlack = isBlackNode(parent);
                colorNodeToBlack(parent);
                colorNodeToRed(sibling);

                if (isParentBlack) {
                    afterRemove(parent);
                }
            } else {
                // sibling has one red node at least
                // borrow a node from sibling node
                if (isBlackNode(sibling.right)) {
                    // left is black, rotate sibling left
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                colorNodeToBlack(sibling.right);
                colorNodeToBlack(parent);

                rotateLeft(parent);
            }
        } else {
            // right side, sibling node on the left

            if (isRedNode(sibling)) {
                colorNodeToBlack(sibling);
                colorNodeToRed(parent);
                rotateRight(parent);

                sibling = parent.left;
            }

            // sibling node must be black
            if (isBlackNode(sibling.left) && isBlackNode(sibling.right)) {
                // sibling doesn't have any red node, parent node combine with sibling node
                boolean isParentBlack = isBlackNode(parent);
                colorNodeToBlack(parent);
                colorNodeToRed(sibling);

                if (isParentBlack) {
                    afterRemove(parent);
                }
            } else {
                // sibling has one red node at least
                // borrow a node from sibling node
                if (isBlackNode(sibling.left)) {
                    // left is black, rotate sibling left
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                colorNodeToBlack(sibling.left);
                colorNodeToBlack(parent);

                rotateRight(parent);
            }
        }

    }

    /**
     * set color to node
     * @param node node
     * @param color Color Enum, Red or Black
     * @return colored node
     */
    private Node<E> color(Node<E> node, NodeColor color) {
        if (node == null) { return node; }

        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> colorNodeToRed(Node<E> node) {
        color(node, NodeColor.RED);
        return node;
    }

    private Node<E> colorNodeToBlack(Node<E> node) {
        color(node, NodeColor.BLACK);
        return node;
    }

    private NodeColor colorOf(Node<E> node) {
        return node == null ? NodeColor.BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlackNode(Node<E> node) {
        return colorOf(node) == NodeColor.BLACK;
    }

    private boolean isRedNode(Node<E> node) {
        return colorOf(node) == NodeColor.RED;
    }
}

package com.rocky.ao.tree;

import com.rocky.utils.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author yun.ao
 * @date 2022/11/24 16:55
 * @description
 */
public class AVLTree<E> extends BinarySearchTree<E> {
    public static void main(String[] args) {
        Integer[] integers = { 74, 98, 16, 51, 25, 9, 83, 42, 76, 79 };

        AVLTree<Integer> avl = new AVLTree<>();

        for (int value: integers) {
            avl.add(value);
        }

        BinaryTrees.print(avl);
//        bst.preorderTraversal();
//
//        bst.inorderTraversal();
//        bst.postorderTraversal();
//        avl.levelOrderTraversal(new Visitor<Integer>() {
//            @Override
//            boolean visit(Integer element) {
//                System.out.print(element + ",");
//
//                return element == 2 ? true : false;
//            }
//        });
    }

    AVLTree() {
        this(null);
    }

    AVLTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                restoreBalance(node);
                break;
            }
        }
    }

    /**
     * jude node is balanced
     * if |balanceFactor| <= 1 then it is balanced
     * @param node node
     * @return true/false
     */
    private boolean isBalanced(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode<E>)node).updateHeight();
    }

    /**
     * restore balance
     * @param grand unbalance node at min height
     */
    private void restoreBalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerNode();
        Node<E> node = ((AVLNode<E>)parent).tallerNode();
        if (parent.isLeftNode()) { // L
            if (node.isLeftNode()) { // LL
                rotateRight(grand);
            } else { // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isLeftNode()) { // RL
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                rotateLeft(grand);
            }
        }
    }

    private void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;

        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }
    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<E> grand, Node<E>parent, Node<E> child) {
        // 让parent成为子树根节点
        parent.parent = grand.parent;

        if (grand.isLeftNode()) {
            grand.parent.left = parent;
        } else if (grand.isRightNode()) {
            grand.parent.right = parent;
        } else {
            // root node
            root = parent;
        }

        // 更新child 的parent
        if (child != null) {
            child.parent = grand;
        }

        // 更新grand 的parent
        grand.parent = parent;

        updateHeight(grand);
        updateHeight(parent);
    }
}

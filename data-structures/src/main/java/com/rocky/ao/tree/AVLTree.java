package com.rocky.ao.tree;

import com.rocky.utils.printer.BinaryTrees;

import java.util.Comparator;

/**
 * @author yun.ao
 * @date 2022/11/24 16:55
 * @description
 */
public class AVLTree<E> extends BalanceBinarySearchTree<E> {
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

    @Override
    protected void afterRemove(Node<E> node) {
        while ((node = node.parent) != null) {
            if (isBalanced(node)) {
                updateHeight(node);
            } else {
                restoreBalance(node);
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

    private void restoreBalance(Node<E> grand) {
        Node<E> parent = ((AVLNode<E>)grand).tallerNode();
        Node<E> node = ((AVLNode<E>)parent).tallerNode();
        if (parent.isLeftNode()) { // L
            if (node.isLeftNode()) { // LL
                rotate(grand, node, node.right, parent, parent.right, grand);
            } else { // LR
                rotate(grand, parent, node.left, node, node.right, grand);
            }
        } else { // R
            if (node.isLeftNode()) { // RL
                rotate(grand, grand, node.left, node, node.right, parent);
            } else { // RR
                rotate(grand, grand, parent.left, parent, node.left, node);
            }
        }
    }


    /**
     * restore balance
     * @param grand unbalance node at min height
     */
    private void restoreBalance2(Node<E> grand) {
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

    @Override
    protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
        super.afterRotate(grand, parent, child);

        updateHeight(grand);
        updateHeight(parent);
    }

    @Override
    protected void rotate(Node<E> r, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f) {
        super.rotate(r, b, c, d, e, f);

        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }
}

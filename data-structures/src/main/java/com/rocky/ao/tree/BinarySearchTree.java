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
public class BinarySearchTree<E> implements BinaryTreeInfo {
    public static abstract class Visitor<E> {
        boolean isStop;

        abstract boolean visit(E element);
    }

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

    public void clear() {
        root = null;
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

    private Node<E> predecessor(Node<E> node) {
        if (node == null) { return null; };

        Node<E> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }

        // search in parent node
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }

        return node.parent;
    }

    private Node<E> successor(Node<E> node) {
        if (node == null) { return null; };

        Node<E> p = node.right;
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }

        // search in parent node
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    public int height() {
        if (root == null) { return 0; }

        int height = 0;

        // store level size, when level size equals to zero,
        // let height add one.
        int levelSize = 1;

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            levelSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levelSize == 0) {
                levelSize = queue.size();
                height += 1;
            }
        }
        return height;
    }

    public int height1() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) { return 0; }
        return 1 + Math.max(height(node.left), height(node.right));
    }


    public boolean isCompleteBinaryTree() {
        if (root == null) { return false; }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        boolean leaf = false;

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            if (leaf && !node.isLeaf()) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                leaf = true;
            }
        }
        return false;
    }

    // traversal
    // preorder traversal
    public void preorderTraversal(Visitor<E> visitor) {
        System.out.println("start ------- preorder traversal");
        preorderTraversal(root, visitor);
        System.out.println("end ------- preorder traversal");
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.isStop) { return; }
        visitor.isStop = visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    // inorder traversal
    public void inorderTraversal(Visitor<E> visitor) {
        System.out.println("start ------- inorder traversal");
        inorderTraversal(root, visitor);
        System.out.println("end ------- inorder traversal");
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.isStop) { return; }

        inorderTraversal(node.left, visitor);
        if (visitor.isStop) {
            return;
        }
        visitor.isStop = visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }

    // postorder traversal
    public void postorderTraversal(Visitor<E> visitor) {
        System.out.println("start ------- postorder traversal");
        postorderTraversal(root, visitor);
        System.out.println("end ------- postorder traversal");
    }

    private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.isStop) { return; }

        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);

        if (visitor.isStop) {
            return;
        }
        visitor.visit(node.element);
    }

    // level order traversal
    public void levelOrderTraversal(Visitor<E> visitor) {
        System.out.println("start ------- level order traversal");
        if (root == null || visitor == null) { return; }

        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            if (visitor.visit(node.element)) {
                return;
            }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        System.out.println("end ------- level order traversal");
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

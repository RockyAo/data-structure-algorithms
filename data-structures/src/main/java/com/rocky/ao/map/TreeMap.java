package com.rocky.ao.map;

import com.rocky.ao.protocols.Visitor;
import com.rocky.ao.tree.Node;
import com.rocky.ao.tree.NodeColor;
import com.rocky.ao.tree.RBNode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yun.ao
 * @date 2022/11/30 10:41
 * @description
 */
public class TreeMap<K, V> implements Map<K, V> {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("a", 5);
        map.put("b", 2);
        map.put("c", 10);
        map.put("a", 9);

        map.traversal(new Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println("key: " + key + " value: " + value);
                return false;
            }
        });

    }
    private int size;
    private Node<K, V> root;

    private Comparator<K> comparator;

    public TreeMap() {
        this(null);
    }

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        keyEmptyCheck(key);

        if (root == null) {
            // add first node
            root = new Node<>(key, value);
            size ++;

            afterPut(root);
            return null;
        }

        // not first node

        // find the parent node
        Node<K, V> node = root;
        Node<K, V> parentNode = root;
        V oldValue = node.value;
        int cmp = 0;
        while (node != null) {
            cmp = compare(key, node.key);
            parentNode = node;
            if (cmp > 0 ) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.value = value;
                node.key = key;
                return oldValue;
            }
        }

        // add new node
        Node<K, V> newNode = new Node<>(key, value, parentNode);
        if (cmp > 0) {
            parentNode.right = newNode;
        } else {
            parentNode.left = newNode;
        }

        size++;

        afterPut(newNode);
        return oldValue;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    private V remove(Node<K,V> node) {
        if (node == null) { return null; }

        V oldValue = node.value;

        if (node.hasTwoChildren()) { //度为2的节点
            Node<K, V> successor = successor(node);
            node.key = successor.key;
            node.value = successor.value;
            node = successor;
        }

        Node<K, V> replacement = node.left != null ? node.left : node.right;

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

            afterRemove(replacement);
        } else if (node.parent == null) {
            // 叶子且为根节点
            root = null;
            afterRemove(node);
        } else {
            // 叶子
            if (node == node.parent.right) {
                node.parent.right = null;
            } else {
                node.parent.left = null;
            }
            afterRemove(node);
        }

        size--;
        return oldValue;
    }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (root == null) { return false; }

        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<K, V> node = queue.poll();

            if (compareValues(value, node.value)) { return true; }

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) { return; }

        inorderTraversal(node.left, visitor);
        if (visitor.stop) {
            return;
        }
        visitor.stop = visitor.visit(node.key, node.value);
        inorderTraversal(node.right, visitor);
    }

    private boolean compareValues(V v1, V v2) {
        return v1 == null ? v2 == null : v1.equals(v2);
    }

    private void afterPut(Node<K,V> node) {
        Node<K,V> parent = node.parent;

        if (parent == null) {
            // root node color to black
            colorNodeToBlack(node);
            return;
        }

        if (isBlackNode(parent)) { return; }

        Node<K, V> sibling = parent.sibling();
        Node<K, V> grand = colorNodeToRed(parent.parent);

        if (isRedNode(sibling)) {
            colorNodeToBlack(parent);
            colorNodeToBlack(sibling);

            // let grand node as a new node add it again
            afterPut(grand);
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

    protected void afterRemove(Node<K, V> node) {
        if (isRedNode(node)) {
            // color to black
            colorNodeToBlack(node);
            return;
        }

        // delete black node
        Node<K, V> parent = node.parent;

        if (parent == null) { return; }

        // get delete node is left or right
        boolean left = parent.left == null;

        Node<K, V> sibling = left ? parent.right : parent.left;

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

    private Node<K, V> node(K key) {
        Node<K, V> node = root;

        while (node != null) {
            int cmp = compare(key, node.key);

            if (cmp == 0) { return node; }
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    private Node<K, V> successor(Node<K, V> node) {
        if (node == null) { return null; };

        Node<K, V> p = node.right;
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

    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;

        grand.right = child;
        parent.left = grand;

        afterRotate(grand, parent, child);
    }
    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(grand, parent, child);
    }

    private void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
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
    }

    /**
     * compare two values
     * @param k1 value1
     * @param k2 value2
     * @return ComparedResult
     */
    private int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        }
        return ((Comparable<K>)k1).compareTo(k2);
    }

    private void keyEmptyCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("element can not be null");
        }
    }

    /**
     * set color to node
     * @param node node
     * @param color Color Enum, Red or Black
     * @return colored node
     */
    private Node<K, V> color(Node<K, V> node, NodeColor color) {
        if (node == null) { return node; }

        node.color = color;
        return node;
    }

    private Node<K, V> colorNodeToRed(Node<K, V> node) {
        color(node, NodeColor.RED);
        return node;
    }

    private Node<K, V> colorNodeToBlack(Node<K, V> node) {
        color(node, NodeColor.BLACK);
        return node;
    }

    private NodeColor colorOf(Node<K, V> node) {
        return node == null ? NodeColor.BLACK : node.color;
    }

    private boolean isBlackNode(Node<K, V> node) {
        return colorOf(node) == NodeColor.BLACK;
    }

    private boolean isRedNode(Node<K, V> node) {
        return colorOf(node) == NodeColor.RED;
    }

    private static class Node<K, V> {
        public K key;
        public V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, Node<K, V> left, Node<K, V> right, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public Node(K key, V value, Node<K, V> parent) {
            this(key, value, null, null, parent);
        }

        public Node(K key, V value) {
            this(key, value, null, null, null);
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftNode() {
            return parent != null && this == parent.left;
        }

        public boolean isRightNode() {
            return parent != null && this == parent.right;
        }

        public NodeColor color = NodeColor.RED;

        // sibling node
        public Node<K, V> sibling() {
            if (isLeftNode()) {
                return parent.right;
            }

            if (isRightNode()) {
                return parent.left;
            }

            return null;
        }
    }
}

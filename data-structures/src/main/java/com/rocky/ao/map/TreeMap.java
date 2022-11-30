package com.rocky.ao.map;

import com.rocky.ao.tree.Node;
import com.rocky.ao.tree.NodeColor;
import com.rocky.ao.tree.RBNode;

/**
 * @author yun.ao
 * @date 2022/11/30 10:41
 * @description
 */
public class TreeMap<K, V> implements Map<K, V> {
    private int size;
    private Node<K, V> root;

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
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V Value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

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

package com.rocky.ao.map;

import com.rocky.ao.tree.NodeColor;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * @author yun.ao
 * @date 2022/11/30 16:20
 * @description
 */
public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 1 << 4;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private int size;

    private Node<K, V>[] table;

    private Comparator<K> comparator;

    public HashMap() {
        this(null);
    }

    public HashMap(Comparator<K> comparator) {
        this.comparator = comparator;
        table = new Node[DEFAULT_CAPACITY];
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
        if (isEmpty()) { return; }
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        int index = index(key);

        Node<K, V> root = table[index];

        if (root == null) {
            root = new Node<>(key, value);
            table[index] = root;
            size++;
            fixAfterPut(root);
            return null;
        }

        // add new node to tree
        Node<K, V> node = root;
        Node<K, V> parent = root;
        int cmp = 0;
        K k1 = key;
        int h1 = hash(k1);
        Node<K, V> result = null;
        boolean searched = false; // 是否已经搜索过这个key
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.keyHash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(k1, k2)) {
                cmp = 0;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
            } else if (searched) { // 已经扫描了
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            } else { // searched == false; 还没有扫描，然后再根据内存地址大小决定左右
                if ((node.left != null && (result = node(node.left, k1)) != null)
                        || (node.right != null && (result = node(node.right, k1)) != null)) {
                    // 已经存在这个key
                    node = result;
                    cmp = 0;
                } else { // 不存在这个key
                    searched = true;
                    cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
                }
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else { // 相等
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                node.keyHash = h1;
                return oldValue;
            }
        } while (node != null);

        // add new node
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        size++;

        fixAfterPut(newNode);

        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node != null ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    protected V remove(Node<K, V> node) {
        if (node == null) return null;

        Node<K, V> willNode = node;

        size--;

        V oldValue = node.value;

        if (node.hasTwoChildren()) { // 度为2的节点
            // 找到后继节点
            Node<K, V> s = successor(node);
            // 用后继节点的值覆盖度为2的节点的值
            node.key = s.key;
            node.value = s.value;
            node.keyHash = s.keyHash;
            // 删除后继节点
            node = s;
        }

        // 删除node节点（node的度必然是1或者0）
        Node<K, V> replacement = node.left != null ? node.left : node.right;
        int index = index(node);

        if (replacement != null) { // node是度为1的节点
            // 更改parent
            replacement.parent = node.parent;
            // 更改parent的left、right的指向
            if (node.parent == null) { // node是度为1的节点并且是根节点
                table[index] = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else { // node == node.parent.right
                node.parent.right = replacement;
            }

            // 删除节点之后的处理
            fixAfterRemove(replacement);
        } else if (node.parent == null) { // node是叶子节点并且是根节点
            table[index] = null;
        } else { // node是叶子节点，但不是根节点
            if (node == node.parent.left) {
                node.parent.left = null;
            } else { // node == node.parent.right
                node.parent.right = null;
            }

            // 删除节点之后的处理
            fixAfterRemove(node);
        }

        // 交给子类去处理
        afterRemove(willNode, node);

        return oldValue;
    }

    protected void afterRemove(Node<K, V> willNode, Node<K, V> removedNode) { }

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        if (isEmpty()) { return false; }

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) { continue; }

            queue.offer(table[i]);

            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (Objects.equals(value, node.value)) {
                    return true;
                }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }

        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (size == 0 || visitor == null) { return; }

        Queue<Node<K, V>> queue = new LinkedList<>();

        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) { continue; }

            queue.offer(table[i]);

            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();

                if (visitor.visit(node.key, node.value)) { return; }

                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
    }


    private int index(K key) {
        if (key == null) { return 0; }
        int keyHash = key.hashCode();
        keyHash = keyHash ^ (keyHash >>> 16);
        return keyHash & (table.length - 1);
    }

    private int index(Node<K, V> node) {
        if (node == null) { return 0; }
        int keyHash = node.keyHash;
        keyHash = keyHash ^ (keyHash >>> 16);
        return keyHash & (table.length - 1);
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

    private void fixAfterPut(Node<K, V> node) {
        Node<K, V> parent = node.parent;

        // 添加的是根节点 或者 上溢到达了根节点
        if (parent == null) {
            colorNodeToBlack(node);
            return;
        }

        // 如果父节点是黑色，直接返回
        if (isBlackNode(parent)) return;

        // 叔父节点
        Node<K, V> uncle = parent.sibling();
        // 祖父节点
        Node<K, V> grand = colorNodeToRed(parent.parent);
        if (isRedNode(uncle)) { // 叔父节点是红色【B树节点上溢】
            colorNodeToBlack(parent);
            colorNodeToBlack(uncle);
            // 把祖父节点当做是新添加的节点
            fixAfterPut(grand);
            return;
        }

        // 叔父节点不是红色
        if (parent.isLeftNode()) { // L
            if (node.isLeftNode()) { // LL
                colorNodeToBlack(parent);
            } else { // LR
                colorNodeToBlack(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isLeftNode()) { // RL
                colorNodeToBlack(node);
                rotateRight(parent);
            } else { // RR
                colorNodeToBlack(parent);
            }
            rotateLeft(grand);
        }
    }

    private void fixAfterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRedNode(node)) {
            colorNodeToBlack(node);
            return;
        }

        Node<K, V> parent = node.parent;
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftNode();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRedNode(sibling)) { // 兄弟节点是红色
                colorNodeToBlack(sibling);
                colorNodeToRed(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlackNode(sibling.left) && isBlackNode(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlackNode(parent);
                colorNodeToBlack(parent);
                colorNodeToRed(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlackNode(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                colorNodeToBlack(sibling.right);
                colorNodeToBlack(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRedNode(sibling)) { // 兄弟节点是红色
                colorNodeToBlack(sibling);
                colorNodeToRed(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlackNode(sibling.left) && isBlackNode(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlackNode(parent);
                colorNodeToBlack(parent);
                colorNodeToRed(sibling);
                if (parentBlack) {
                    fixAfterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlackNode(sibling.left)) {
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
        Node<K, V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    private Node<K, V> node(Node<K, V> node, K k1) {
        int h1 = hash(k1);
        // 存储查找结果
        Node<K, V> result = null;
        int cmp = 0;
        while (node != null) {
            K k2 = node.key;
            int h2 = node.keyHash;
            // 先比较哈希值
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right, k1)) != null) {
                return result;
            } else { // 只能往左边找
                node = node.left;
            }
        }
        return null;
    }

    private int hash(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }


    private void resize() {
        // 装填因子 <= 0.75
        if (size / table.length <= DEFAULT_LOAD_FACTOR) return;

        Node<K, V>[] oldTable = table;
        table = new Node[oldTable.length << 1];

        Queue<Node<K, V>> queue = new LinkedList<>();
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null) continue;

            queue.offer(oldTable[i]);
            while (!queue.isEmpty()) {
                Node<K, V> node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

                // 挪动代码得放到最后面
                moveNode(node);
            }
        }
    }

    private void moveNode(Node<K, V> newNode) {
        // 重置
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        newNode.color = NodeColor.RED;

        int index = index(newNode);
        // 取出index位置的红黑树根节点
        Node<K, V> root = table[index];
        if (root == null) {
            root = newNode;
            table[index] = root;
            fixAfterPut(root);
            return;
        }

        // 添加新的节点到红黑树上面
        Node<K, V> parent = root;
        Node<K, V> node = root;
        int cmp = 0;
        K k1 = newNode.key;
        int h1 = newNode.keyHash;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.keyHash;
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (k1 != null && k2 != null
                    && k1 instanceof Comparable
                    && k1.getClass() == k2.getClass()
                    && (cmp = ((Comparable)k1).compareTo(k2)) != 0) {
            } else {
                cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
            }

            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            }
        } while (node != null);

        // 看看插入到父节点的哪个位置
        newNode.parent = parent;
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        // 新添加节点之后的处理
        fixAfterPut(newNode);
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
            table[index(grand)] = parent;
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
    private int compare(K k1, K k2, int h1, int h2) {
        int result = h1 - h2;

        if (result != 0) { return result; }

        if (Objects.equals(k1, k2)) { return 0; }

        // hash value equals, but not same key
        if (k1 != null && k2 != null) {
            String k1Class = k1.getClass().getName();
            String k2Class = k2.getClass().getName();

            result = k1Class.compareTo(k2Class);

            if (result != 0) { return result; }

            if (k1 instanceof Comparable) {
                return ((Comparable)k1).compareTo(k2);
            }
        }

        // same type but can't compare
        // k1 = null || k2 == null
        // use system RAM address
        return System.identityHashCode(k1) - System.identityHashCode(k2);
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
        public int keyHash;
        public K key;
        public V value;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;

        public Node(K key, V value, Node<K, V> left, Node<K, V> right, Node<K, V> parent) {
            this.key = key;
            this.keyHash = key == null ? 0 : key.hashCode();
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

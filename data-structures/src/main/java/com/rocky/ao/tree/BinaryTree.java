package com.rocky.ao.tree;
import com.rocky.ao.protocols.Visitor;
import com.rocky.utils.printer.BinaryTreeInfo;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author yun.ao
 * @date 2022/11/23 17:37
 * @description
 */
public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
    }

    protected Node<E> predecessor(Node<E> node) {
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

    protected Node<E> successor(Node<E> node) {
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

        // use stack
        Stack<Node<E>> stack = new Stack<>();

        while (true) {
            if (node != null) {
                // visit node
                if (visitor.visit(node.element)) { return; }

                if (node.right != null) {
                    stack.push(node.right);
                }

                node = node.left;
            } else if (stack.isEmpty()) {
                break;
            } else {
                node = stack.pop();
            }
        }
    }

    private void preorderTraversal2(Node<E> node, Visitor<E> visitor) {
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
        return ((Node<E>)node);
    }
}

package com.rocky.ao.set;

import com.rocky.ao.protocols.Visitor;
import com.rocky.ao.tree.RBTree;

/**
 * @author yun.ao
 * @date 2022/11/29 16:58
 * @description
 */
public class TreeSet<E> implements Set<E> {
    public static void main(String[] args) {
        Set<Integer> listSet = new TreeSet<>();
        listSet.add(10);
        listSet.add(11);
        listSet.add(12);
        listSet.add(10);
        listSet.add(11);
        listSet.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
    private RBTree<E> tree = new RBTree<>();

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public void clear() {
        tree.clear();
    }

    @Override
    public boolean contains(E element) {
        return tree.contains(element);
    }

    @Override
    public void add(E element) {
        tree.add(element);
    }

    @Override
    public void remove(E element) {
        tree.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        tree.inorderTraversal(visitor);
    }
}

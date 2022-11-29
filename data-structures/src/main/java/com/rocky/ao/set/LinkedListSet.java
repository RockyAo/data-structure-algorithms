package com.rocky.ao.set;

import com.rocky.ao.protocols.Visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yun.ao
 * @date 2022/11/29 16:58
 * @description
 */
public class LinkedListSet<E> implements Set<E> {
    public static void main(String[] args) {
        Set<Integer> listSet = new LinkedListSet<>();
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


    private List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        if (list.contains(element)) {
            return;
        }
        list.add(element);
    }

    @Override
    public void remove(E element) {
        list.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) { return; }

        for (int i = 0; i < list.size(); i++) {
            if (visitor.visit(list.get(i))) { return; }
        }
    }
}

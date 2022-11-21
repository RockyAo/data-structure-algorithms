package com.rocky.ao.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yun.ao
 * @date 2022/11/21 15:28
 * @description
 */
public class Stack<E> {
    private List<E> list = new ArrayList();

    public int size() {
        return list.size();
    }

    public void push(E element) {
        list.add(element);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E top() {
        return list.get(list.size() - 1);
    }
}

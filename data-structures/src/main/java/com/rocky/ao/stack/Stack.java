package com.rocky.ao.stack;

import com.rocky.ao.arraylist.ArrayList;

/**
 * @author yun.ao
 * @date 2022/11/21 15:28
 * @description
 */
public class Stack<E> extends ArrayList<E> {
    public void push(E element) {
        add(element);
    }

    public E pop() {
        return remove(size - 1);
    }

    public E top() {
        return get(size - 1);
    }
}

package com.rocky.ao.set;

/**
 * @author yun.ao
 * @date 2022/11/29 16:57
 * @description
 */
public interface Set<E> {
    int size();
    boolean isEmpty();
    void clear();
    boolean contains(E element);
    void add(E element);
    void remove(E element);
    void traversal(Visitor<E> visitor);

    public static abstract class Visitor<E> {
        boolean isStop;
        abstract boolean visit(E element);
    }
}

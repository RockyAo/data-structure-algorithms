package com.rocky.ao.set;

import com.rocky.ao.protocols.Visitor;

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

}

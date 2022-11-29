package com.rocky.ao.protocols;

/**
 * @author yun.ao
 * @date 2022/11/29 17:11
 * @description
 */
public abstract class Visitor<E> {
    public boolean isStop;

    public abstract boolean visit(E element);
}

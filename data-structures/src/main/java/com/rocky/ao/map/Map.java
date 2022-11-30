package com.rocky.ao.map;

/**
 * @author yun.ao
 * @date 2022/11/30 10:36
 * @description
 */
public interface Map<K, V> {
    int size();
    boolean isEmpty();
    void clear();
    V put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
    boolean containsValue(V Value);
    void traversal(Visitor<K, V> visitor);

    public static abstract class Visitor<K, V> {
        public boolean stop;
        public abstract boolean visit(K key, V value);
    }
}

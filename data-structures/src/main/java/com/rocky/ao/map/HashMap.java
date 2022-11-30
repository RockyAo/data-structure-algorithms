package com.rocky.ao.map;

/**
 * @author yun.ao
 * @date 2022/11/30 16:20
 * @description
 */
public class HashMap<K, V> implements Map<K, V> {
    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V Value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }
}

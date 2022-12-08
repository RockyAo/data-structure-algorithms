package com.rocky.ao.sort;

/**
 * @author yun.ao
 * @date 2022/12/8 17:23
 * @description
 */
public abstract class Sort<T> {
    protected T[] data;

    public void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        this.data = array;

        sort();
    }

    protected abstract void sort();
}

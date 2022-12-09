package com.rocky.ao.sort;

/**
 * @author yun.ao
 * @date 2022/12/7 10:55
 * @description
 */
public class InsertionSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int start = 1; start < data.length; start++) {
            int current = start;

            while (current > 0 && cmp(current, current-1) < 0) {
                swap(current, current-1);
                current--;
            }
        }
    }
}

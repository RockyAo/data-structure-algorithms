package com.rocky.ao.sort;

/**
 * @author yun.ao
 * @date 2022/12/7 10:55
 * @description
 */
public class InsertionSort2<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        for (int start = 1; start < data.length; start++) {
            int current = start;

            T currentValue = data[current];

            while (current > 0 && cmp(currentValue, data[current - 1]) < 0) {
                data[current] = data[current - 1];
                current--;
            }

            data[current] = currentValue;
        }
    }
}

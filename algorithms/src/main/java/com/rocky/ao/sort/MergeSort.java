package com.rocky.ao.sort;

/**
 * @author yun.ao
 * @date 2023/1/29 14:46
 * @description 归并排序
 */
public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    private T[] leftArray;

    @Override
    protected void sort() {
        leftArray = (T[]) new Comparable[data.length >> 1];
        sort(0, data.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) { return; }
        int mid = (begin + end) >> 1;

        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    private void merge(int begin, int mid, int end) {
        // 左边数组index
        int li = 0, lend = mid - begin;
        // 右边数组
        int ri = mid, rend = end;
        // array 索引
        int ai = begin;

        for (int i = 0; i < lend; i++) {
            // 备份左侧数组
            leftArray[i] = data[begin + i];
        }

        while (li < lend) {
            if (ri < rend && cmp(data[ri], leftArray[li]) < 0) {
                data[ai++] = data[ri++];
            } else {
                data[ai++] = leftArray[li++];
            }
        }
    }
}

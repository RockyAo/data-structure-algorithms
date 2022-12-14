package com.rocky.ao.sort;

/**
 * @author yun.ao
 * @date 2022/12/7 10:55
 * @description
 */
public class BubbleSort3<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int end = data.length - 1; end > 0; end--) {
            // 记录已经排好的位置
            int sortedIndex = 1;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin-1) < 0) {
                    swap(begin, begin - 1);
                    sortedIndex = begin;
                }
            }
            end = sortedIndex;
        }
    }
}

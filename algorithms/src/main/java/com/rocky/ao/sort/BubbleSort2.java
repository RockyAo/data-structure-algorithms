package com.rocky.ao.sort;

/**
 * @author yun.ao
 * @date 2022/12/7 10:55
 * @description
 */
public class BubbleSort2<T extends Comparable<T>> extends Sort<T> {
    @Override
    protected void sort() {
        for (int end = data.length - 1; end > 0; end--) {
            // 如果元数据本身就是有顺序的可以直接结束
            boolean sorted = true;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(begin, begin-1) < 0) {
                    swap(begin, begin - 1);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }
}

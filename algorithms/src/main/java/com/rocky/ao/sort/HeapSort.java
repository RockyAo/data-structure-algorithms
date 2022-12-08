package com.rocky.ao.sort;

/**
 * @author yun.ao
 * @date 2022/12/7 10:55
 * @description
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    private int heapSize;

    @Override
    protected void sort() {
        // 原地建堆
        heapSize = data.length;

        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (heapSize > 1) {
            // 交换对顶元素和尾部元素
            swap(0, --heapSize);

            // 对0位置下滤, 恢复二叉堆性质
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        T element = data[index];

        int half = heapSize >> 1;

        while (index < half) {
            // index 必须是非叶子节点
            // 左边和父节点比
            int childIndex = (index << 1) + 1;
            T child = data[childIndex];

            int rightIndex = childIndex + 1;

            // 右子节点比左子结点大
            if (rightIndex < heapSize && cmp(data[rightIndex], child) > 0) {
                child = data[childIndex = rightIndex];
            }

            // 大于等于子节点
            if (cmp(element, child) >= 0) {
                break;
            }

            data[index] = child;
            index = childIndex;
        }

        data[index] = element;
    }
}

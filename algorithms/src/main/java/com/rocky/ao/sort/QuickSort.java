package com.rocky.ao.sort;

/**
 * @author yun.ao
 * @date 2023/1/31 21:33
 * @description
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        sort(0, data.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) return;

        // 确定轴点位置 O(n)
        int mid = pivotIndex(begin, end);
        // 对子序列进行快速排序
        sort(begin, mid);
        sort(mid + 1, end);
    }

    private int pivotIndex(int begin, int end) {
        // 随机选择一个元素跟begin位置进行交换
        swap(begin, begin + (int)(Math.random() * (end - begin)));

        // 备份begin位置的元素
        T pivot = data[begin];
        // end指向最后一个元素
        end--;

        boolean direction = true;

        while (begin < end) {
            if (direction) {
                if (cmp(pivot, data[end]) < 0) { // 右边元素 > 轴点元素
                    end--;
                } else { // 右边元素 <= 轴点元素
                    data[begin++] = data[end];
                    direction = false;
                }
            } else {
                if (cmp(pivot, data[begin]) > 0) { // 左边元素 < 轴点元素
                    begin++;
                } else { // 左边元素 >= 轴点元素
                    data[end--] = data[begin];
                    direction = true;
                }
            }
//            while (begin < end) {
//                if (cmp(pivot, data[end]) < 0) { // 右边元素 > 轴点元素
//                    end--;
//                } else { // 右边元素 <= 轴点元素
//                    data[begin++] = data[end];
//                    break;
//                }
//            }
//            while (begin < end) {
//                if (cmp(pivot, data[begin]) > 0) { // 左边元素 < 轴点元素
//                    begin++;
//                } else { // 左边元素 >= 轴点元素
//                    data[end--] = data[begin];
//                    break;
//                }
//            }
        }

        // 将轴点元素放入最终的位置
        data[begin] = pivot;
        // 返回轴点元素的位置
        return begin;
    }
}

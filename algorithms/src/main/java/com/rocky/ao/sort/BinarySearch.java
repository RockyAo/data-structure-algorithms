package com.rocky.ao.sort;

import com.rocky.utils.Integers;

/**
 * @author yun.ao
 * @date 2022/12/9 15:56
 * @description
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

        int index = search(array, 3);

        System.out.println("index: " + index);
    }
    public static int search(int[] array, int v) {
        if (array == null || array.length == 0) { return -1; }

        int begin = 0;
        int end = array.length;

        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else if (v > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

package com.rocky.ao.array;

import java.util.Arrays;

/**
 * @author yun.ao
 * @date 2023/1/31 21:39
 * @description
 * https://leetcode.com/problems/merge-sorted-array/
 * https://leetcode.cn/problems/merge-sorted-array/description/
 */
public class _88_MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i != n; ++i) {
            nums1[m + i] = nums2[i];
        }
        Arrays.sort(nums1);
    }
}

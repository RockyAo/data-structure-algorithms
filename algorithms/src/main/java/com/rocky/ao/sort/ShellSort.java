package com.rocky.ao.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yun.ao
 * @date 2023/2/1 22:30
 * @description 希尔排序
 */
public class ShellSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    protected void sort() {
        List<Integer> stepSequence = getStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }
    }

    private void sort(int step) {
        // col : 第几列，column的简称
        for (int col = 0; col < step; col++) { // 对第col列进行排序
            // col、col+step、col+2*step、col+3*step
            for (int begin = col + step; begin < data.length; begin += step) {
                int cur = begin;
                while (cur > col && cmp(cur, cur - step) < 0) {
                    swap(cur, cur - step);
                    cur -= step;
                }
            }
        }
    }
    private List<Integer> getStepSequence() {
        ArrayList<Integer> stepSequence = new ArrayList<>();

        int step = data.length;

        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }
}

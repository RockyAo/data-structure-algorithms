package com.rocky.ao.sort;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @author yun.ao
 * @date 2022/12/8 17:23
 * @description
 */
public abstract class Sort<T extends Comparable<T>> implements Comparable<Sort<T>> {
    protected T[] data;
    private int cmpCount;
    private int swapCount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(T[] array) {
        if (array == null || array.length <= 1) {
            return;
        }

        this.data = array;

        long start = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - start;
    }

    protected abstract void sort();

    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public int compareTo(Sort<T> o) {
        int result = (int) (time - o.time);

        if (result != 0) { return result; }

        result = cmpCount - o.cmpCount;

        if (result != 0) { return result; }

        return swapCount - o.swapCount;
    }

    @Override
    public String toString() {

        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
//        String stableStr = "稳定性：" + isStable();

        return new StringBuilder()
                .append("--------------------------------------")
                .append("\n")
                .append(getClass().getSimpleName())
                .append("\n")
                .append(timeStr)
                .append("\n")
                .append(compareCountStr)
                .append("\n")
                .append(swapCountStr)
                .append("\n")
                .append("--------------------------------------")
                .toString();
    }

    protected int cmp(int index1, int index2) {
        return cmp(data[index1], data[index2]);
    }

    protected int cmp(T v1, T v2) {
        cmpCount++;
        return v1.compareTo(v2);
    }

    protected void swap(int index1, int index2) {
        swapCount++;
        T tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;
    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }
}

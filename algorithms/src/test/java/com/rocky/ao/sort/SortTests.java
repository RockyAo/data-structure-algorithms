package com.rocky.ao.sort;


import com.rocky.utils.Integers;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.Random;

/**
 * @author yun.ao
 * @date 2022/12/8 17:05
 * @description
 */

public class SortTests {

    @Test
    public void testAllSorts() {
        Integer[] randomData = Integers.random(5000, 0, 10);

        testSorts(
                randomData,
//                new BubbleSort1<>(),
//                new BubbleSort2<>(),
                new BubbleSort3<>(),
                new SelectionSort<>(),
                new HeapSort<>(),
//                new InsertionSort<>(),
                new InsertionSort2<>(),
                new MergeSort<>(),
                new QuickSort<>()
                );
    }

    private void testSorts(Integer[] array, Sort<Integer>... sorts) {
        for (Sort<Integer> sort : sorts) {
            Integer[] newArray = Integers.copy(array);
//            System.out.println(sort.getName() + "排序前: ->");
//            printArray(newArray);

            sort.sort(newArray);
//            System.out.println(sort.getName() + "排序后: ->");
//            printArray(newArray);
            Assert.assertTrue(Integers.isAscOrder(newArray));
        }

        Arrays.sort(sorts);

        for (Sort<Integer> sort : sorts) {
            System.out.println(sort);
        }
    }

    private <T> void printArray(T[] data) {
        StringBuilder builder = new StringBuilder();

        for (T value : data) {
            builder.append(value).append(" ");
        }
        System.out.println(builder);
    }
}

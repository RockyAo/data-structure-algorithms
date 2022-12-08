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
        Integer[] randomData = Integers.random(20, 0, 20);

        testSorts(
                randomData,
                new BubbleSort1<>()
                );
    }

    private void testSorts(Integer[] array, Sort<Integer>... sorts) {
        for (Sort<Integer> sort : sorts) {
            Integer[] newArray = Integers.copy(array);
            Integers.println(newArray);
            sort.sort(newArray);
            Integers.println(newArray);
            Assert.assertTrue(Integers.isAscOrder(newArray));
        }

        Arrays.sort(sorts);

        for (Sort<Integer> sort : sorts) {
            System.out.println(sort);
        }
    }
}

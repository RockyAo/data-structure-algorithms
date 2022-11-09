package com.rocky.ao.arraylist;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * @author yun.ao
 * @date 2022/11/9 10:52
 * @description
 */
public class ArrayListTests {

    @Test
    public void testArrayList() {
        ArrayList<Integer> testArray = new ArrayList<Integer>();

        testArray.add(1);
        testArray.add(3);
        testArray.add(4);

        System.out.println(testArray);

        Assert.assertEquals(testArray.size(), 3);
        Assert.assertTrue(testArray.contains(3));
        Assert.assertFalse(testArray.contains(10));
    }
}

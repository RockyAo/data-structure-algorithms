package com.rocky.ao.linkedlist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * @author yun.ao
 * @date 2022/11/10 17:16
 * @description
 */
public class LinkedListTests {
    private LinkedList<Integer> lists;

    @Before
    public void prepareLists() {
        lists = new LinkedList<>();
    }

    @Test
    public void linkedListAddTest() {
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);

        System.out.println(lists);
        Assert.assertEquals(lists.size(), 4);
    }

    @Test
    public void linkedListAtIndexTest() {

        // 头部插入
        lists.add(0, 1);
        Assert.assertEquals(lists.size(), 1);

        lists.add(2);
        lists.add(3);

        System.out.println(lists);

        // 尾部插入
        lists.add(2, 4);

        Integer value = lists.get(2);

        Assert.assertEquals(value.intValue(), 4);

        System.out.println(lists);
    }
}

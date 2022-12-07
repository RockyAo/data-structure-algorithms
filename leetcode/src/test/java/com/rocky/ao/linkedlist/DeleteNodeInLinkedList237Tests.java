package com.rocky.ao.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yun.ao
 * @date 2022/11/14 15:33
 * @description
 */
public class DeleteNodeInLinkedList237Tests {
    @Test
    public void deleteNode() {
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node1 = new ListNode(1);
        ListNode node9 = new ListNode(9);

        node4.next = node5;
        node5.next = node1;
        node1.next = node9;

        Assert.assertEquals(node1.val, 1);
        Assert.assertEquals(node1.next.val, 9);
        Assert.assertEquals(node5.next.val,1);

        _237_DeleteNodeInLinkedList deleteNodeInLinkedList237 = new _237_DeleteNodeInLinkedList();
        deleteNodeInLinkedList237.deleteNode(node5);
        Assert.assertEquals(node5.val, 1);
        Assert.assertEquals(node5.next.val, 9);
    }
}

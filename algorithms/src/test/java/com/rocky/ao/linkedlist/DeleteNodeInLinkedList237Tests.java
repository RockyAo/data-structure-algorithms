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
        DeleteNodeInLinkedList237.ListNode node4 = new DeleteNodeInLinkedList237.ListNode(4);
        DeleteNodeInLinkedList237.ListNode node5 = new DeleteNodeInLinkedList237.ListNode(5);
        DeleteNodeInLinkedList237.ListNode node1 = new DeleteNodeInLinkedList237.ListNode(1);
        DeleteNodeInLinkedList237.ListNode node9 = new DeleteNodeInLinkedList237.ListNode(9);

        node4.next = node5;
        node5.next = node1;
        node1.next = node9;

        Assert.assertEquals(node1.val, 1);
        Assert.assertEquals(node1.next.val, 9);
        Assert.assertEquals(node5.next.val,1);

        DeleteNodeInLinkedList237 deleteNodeInLinkedList237 = new DeleteNodeInLinkedList237();
        deleteNodeInLinkedList237.deleteNode(node5);
        Assert.assertEquals(node5.val, 1);
        Assert.assertEquals(node5.next.val, 9);
    }
}

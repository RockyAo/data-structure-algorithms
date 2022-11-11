package com.rocky.ao.linkedlist;

/**
 * @author yun.ao
 * @date 2022/11/11 15:54
 * @description
 * https://leetcode.com/problems/delete-node-in-a-linked-list/
 * https://leetcode.cn/problems/delete-node-in-a-linked-list/
 */
public class DeleteNodeInLinkedList237 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

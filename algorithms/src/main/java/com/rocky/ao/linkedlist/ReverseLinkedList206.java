package com.rocky.ao.linkedlist;

/**
 * @author yun.ao
 * @date 2022/11/14 15:59
 * @description
 * https://leetcode.com/problems/reverse-linked-list/
 * https://leetcode.cn/problems/reverse-linked-list/
 */
public class ReverseLinkedList206 {
    public ListNode reverseListRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseListRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

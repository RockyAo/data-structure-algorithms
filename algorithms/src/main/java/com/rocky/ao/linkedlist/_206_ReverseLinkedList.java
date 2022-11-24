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

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = null;
        while (head != null) {
            // let temp node linked to head's next
            ListNode temp = head.next;

            // let head's next linked to newHead
            head.next = newHead;

            // let newHead equals to head
            newHead = head;

            // let head link to temp head
            head = temp;
        }
        return newHead;
    }
}

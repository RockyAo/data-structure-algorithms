package com.rocky.ao.linkedlist;

/**
 * @author yun.ao
 * @date 2022/11/16 16:28
 * @description
 * https://leetcode.com/problems/middle-of-the-linked-list/
 * https://leetcode.cn/problems/middle-of-the-linked-list/
 */
public class MiddleNode876 {
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // fast and slow pointer
        // start position both head
        // when fast pointer wents to end, the slow pointer will in middle.
        // time complexity O(N)
        // space complexity O(1)
        ListNode slowPointer = head;
        ListNode fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        return slowPointer;
    }
}

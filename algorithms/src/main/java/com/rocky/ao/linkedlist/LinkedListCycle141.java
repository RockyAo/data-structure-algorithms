package com.rocky.ao.linkedlist;

/**
 * @author yun.ao
 * @date 2022/11/16 10:50
 * @description
 * https://leetcode.cn/problems/linked-list-cycle/
 * https://leetcode.com/problems/linked-list-cycle/
 */
public class LinkedListCycle141 {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slowPointer = head;
        ListNode fastPointer = head.next;

        while (fastPointer != null && fastPointer.next != null ) {
            if (fastPointer == slowPointer) {
                return true;
            }
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        return false;
    }
}

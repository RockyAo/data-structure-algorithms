package com.rocky.ao.linkedlist;

/**
 * @author yun.ao
 * @date 2022/11/16 16:14
 * @description
 */
public class _83_DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode current = head;

        while (current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

}

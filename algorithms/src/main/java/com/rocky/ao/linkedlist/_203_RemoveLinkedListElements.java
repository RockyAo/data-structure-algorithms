package com.rocky.ao.linkedlist;

/**
 * @author yun.ao
 * @date 2022/11/16 14:19
 * @description
 * https://leetcode.com/problems/remove-linked-list-elements/
 * https://leetcode.cn/problems/remove-linked-list-elements
 */
public class RemoveLinkedListElements203 {
    // recrusion
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements(head.next, val);

        return head.val == val ? head.next : head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        // interation

        return null;
    }
}

package com.rocky.ao.linkedlist;

/**
 * @author yun.ao
 * @date 2022/11/14 15:48
 * @description
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
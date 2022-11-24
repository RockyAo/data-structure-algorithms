package com.rocky.ao.queue;

import java.util.Stack;

/**
 * @author yun.ao
 * @date 2022/11/21 20:05
 * @description
 */
public class _232_ImplementQueueUsingStacks {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public _232_ImplementQueueUsingStacks() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.empty();
    }

    private void checkOutStack() {
        if (outStack.empty()) {
            while (!inStack.empty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}

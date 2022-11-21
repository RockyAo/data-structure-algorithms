package com.rocky.ao.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author yun.ao
 * @date 2022/11/21 16:18
 * @description
 * https://leetcode.cn/problems/score-of-parentheses/
 * https://leetcode.com/problems/score-of-parentheses/
 */
public class ScoreOfParentheses856 {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.add(0);
            } else {
                int cur = stack.pop();
                stack.add(stack.pop() + Math.max(cur * 2 , 1));
            }
        }
        return stack.pop();
    }
}

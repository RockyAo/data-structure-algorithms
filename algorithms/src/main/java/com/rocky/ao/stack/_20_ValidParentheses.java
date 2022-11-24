package com.rocky.ao.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author yun.ao
 * @date 2022/11/21 15:48
 * @description
 * https://leetcode.com/problems/valid-parentheses/
 * https://leetcode.cn/problems/valid-parentheses/
 */
public class ValidParentheses20 {
    public boolean isValid1(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("()", "");
            s = s.replace("[]", "");
        }

        return s.isEmpty();
    }

    public boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char element = s.charAt(i);

            if (element == '(' || element == '{' || element == '[') {
                stack.push(element);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char left = stack.pop();
                if (left == '(' && element != ')') {
                    return false;
                }

                if (left == '[' && element != ']') {
                    return false;
                }

                if (left == '{' && element != '}') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid3(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char element = s.charAt(i);

            if (map.containsKey(element)) {
                stack.push(element);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char left = stack.pop();

                if (element != map.get(left)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}

package com.rocky.ao.stack;

import java.util.Stack;

/**
 * @author yun.ao
 * @date 2022/11/21 17:47
 * @description
 */
public class _150_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String element: tokens) {
            if (isNumber(element)) {
                stack.push(Integer.parseInt(element));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();

                switch (element) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }
}

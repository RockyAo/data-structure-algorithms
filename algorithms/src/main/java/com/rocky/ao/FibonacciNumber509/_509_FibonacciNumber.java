package com.rocky.ao.FibonacciNumber509;

import com.rocky.utils.Times;

/**
 * @author yun.ao
 * @date 2022/11/4 15:55
 * @description fibonacci  美 /ˌfɪbəˈnɑːtʃi/
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
 * such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 *  https://leetcode.com/problems/fibonacci-number/
 */
public class FibonacciNumber {
    public static int fib1(int number) {
        if (number <= 1) {
            return number;
        }
        return fib1(number - 1) + fib1(number - 2);
    }

    public static int fib2(int number) {
        if (number <= 1) {
            return number;
        }

        int first = 0;
        int second = 1;

        for (int i = 0; i < number - 1; i++) {
            int sum = (first + second) % 1000000007;
            first = second;
            second = sum;
        }
        return second;
    }
}

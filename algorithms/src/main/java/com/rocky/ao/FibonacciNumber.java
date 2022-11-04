package com.rocky.ao;

/**
 * @author yun.ao
 * @date 2022/11/4 15:55
 * @description fibonacci  美 /ˌfɪbəˈnɑːtʃi/
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
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(fib1(3));
        System.out.println(fib2(3));
    }
}

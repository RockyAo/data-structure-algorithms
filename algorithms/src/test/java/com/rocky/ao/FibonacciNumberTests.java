package com.rocky.ao;

import com.rocky.ao.FibonacciNumber509._509_FibonacciNumber;
import com.rocky.utils.Times;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author yun.ao
 * @date 2022/11/7 16:18
 * @description fibonacci unit tests
 */
public class FibonacciNumberTests {
    @Test
    public void recursiveTests() {

        Times.test("recursive", () -> {
            Assert.assertEquals(_509_FibonacciNumber.fib1(1), 1);
            Assert.assertEquals(_509_FibonacciNumber.fib1(2), 1);
            Assert.assertEquals(_509_FibonacciNumber.fib1(3), 2);
            Assert.assertEquals(_509_FibonacciNumber.fib1(40), 102334155);
        });
    }

    @Test
    public void betterWayTests() {
        Times.test("better way", () -> {
            Assert.assertEquals(_509_FibonacciNumber.fib2(1), 1);
            Assert.assertEquals(_509_FibonacciNumber.fib2(2), 1);
            Assert.assertEquals(_509_FibonacciNumber.fib2(3), 2);
            Assert.assertEquals(_509_FibonacciNumber.fib2(40), 102334155);
        });
    }
}

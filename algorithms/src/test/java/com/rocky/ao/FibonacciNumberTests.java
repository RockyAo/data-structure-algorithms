package com.rocky.ao;

import com.rocky.utils.Times;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author yun.ao
 * @date 2022/11/7 16:18
 * @description
 */
public class FibonacciNumberTests {
    @Test
    public void recursiveTests() {

        Times.test("recursive", () -> {
            Assert.assertEquals(FibonacciNumber.fib1(1), 1);
            Assert.assertEquals(FibonacciNumber.fib1(2), 1);
            Assert.assertEquals(FibonacciNumber.fib1(3), 2);
            Assert.assertEquals(FibonacciNumber.fib1(40), 102334155);
        });
    }

    @Test
    public void betterWayTests() {
        Times.test("better way", () -> {
            Assert.assertEquals(FibonacciNumber.fib2(1), 1);
            Assert.assertEquals(FibonacciNumber.fib2(2), 1);
            Assert.assertEquals(FibonacciNumber.fib2(3), 2);
            Assert.assertEquals(FibonacciNumber.fib2(40), 102334155);
        });
    }


}
package com.twu.refactoring;

import java.util.Arrays;
import java.util.function.IntPredicate;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
       return getCount(number -> number % 2 == 0);
    }

    private int getCount(IntPredicate predicate) {
        return (int) Arrays.stream(numbers).filter(predicate).count();
    }

    public int countOdd() {
        return getCount(number -> number % 2 == 1);
    }

    public int countPositive() {
        return getCount(number -> number >= 0);
    }

    public int countNegative() {
        return getCount(number -> number < 0);
    }
}

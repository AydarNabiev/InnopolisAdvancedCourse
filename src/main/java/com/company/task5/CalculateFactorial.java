package com.company.task5;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class CalculateFactorial implements Callable<BigInteger> {
    private Integer number;
    private BigInteger factorialOfClosestSmallerNumber;
    private Integer closestSmallerNumber;

    public CalculateFactorial(Integer number, BigInteger factorialOfClosestSmallerNumber, Integer closestSmallerNumber) {
        this.number = number;
        this.factorialOfClosestSmallerNumber = factorialOfClosestSmallerNumber;
        this.closestSmallerNumber = closestSmallerNumber;
    }

    @Override
    public BigInteger call() {
        if (factorialOfClosestSmallerNumber == null) {
            System.out.println("Нету факториала для переиспользования для " + this.number);
            if (number == 0) {
                return BigInteger.ZERO;
            }
            BigInteger result = BigInteger.ONE;
            for (int i = 2; i <= number; i++) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        } else {
            System.out.println("Переиспользовали факториал числа " + this.closestSmallerNumber + " для " + this.number);
            for (int i = closestSmallerNumber; i <= number; i++) {
                factorialOfClosestSmallerNumber = factorialOfClosestSmallerNumber.multiply(BigInteger.valueOf(i));
            }
            return factorialOfClosestSmallerNumber;
        }
    }
}
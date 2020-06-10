package com.company.task5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ListInteraction {

    static void workWithLists(List<Integer> numbers, List<BigInteger> factorials) {
        List<Integer> originals = new ArrayList<>();
        for (Integer number: numbers) {
            if (!originals.contains(number)) {
                int index = findNearestSmallerNumberIndex(originals, number);
                originals.add(number);
                if (index == -1) {
                    factorials.add(new CalculateFactorial(number, null, null).call());
                } else {
                    factorials.add(new CalculateFactorial(number, factorials.get(index), originals.get(index)).call());
                }
            } else {
                originals.add(number);
                factorials.add(factorials.get(originals.indexOf(number)));
            }
        }
    }

    private static int findNearestSmallerNumberIndex(List<Integer> originals, Integer number) {
        Integer nearestSmallerNumber = Integer.MIN_VALUE;
        for (Integer integer: originals) {
            if ((integer < number) && (nearestSmallerNumber < integer) && (integer != 0)) {
                nearestSmallerNumber = integer;
            }
        }
        if (nearestSmallerNumber != Integer.MIN_VALUE) {
            return originals.indexOf(nearestSmallerNumber);
        }
        return -1;
    }
}
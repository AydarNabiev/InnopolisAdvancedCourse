package com.company.task5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.company.task5.ListInteraction.workWithLists;

public class Main {
    final static Random RANDOM = new Random();
    static List<Integer> numbers = new ArrayList<>();
    static List<BigInteger> factorials = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100; i ++) {
            numbers.add(RANDOM.nextInt(100));
        }
        System.out.println(numbers);
        workWithLists(numbers, factorials);
        System.out.println(factorials);
    }
}
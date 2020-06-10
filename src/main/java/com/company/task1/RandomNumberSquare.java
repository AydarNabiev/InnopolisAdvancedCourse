package com.company.task1;

import java.util.Date;
import java.util.Random;

/**
 * RandomNumberSquare.
 * This is a program with n number of random numbers, which gets square roots out each one and uses self multiplication
 * of that root to compare with original number. If number is negative, exception is generated
 *
 *  Version 1.0
 *
 * @author Aydar Nabiev
 */
public class RandomNumberSquare {

    public static final int NUMBERS = 100;
    static Random random = new Random();

    /**
     * main method
     * @param args - array of strings
     * @throws ArithmeticException - происходит, если k < 0
     */
    public static void main(String[] args) throws ArithmeticException {
        for (int i = 0; i < NUMBERS; i++) {
            int k = random.nextInt();
            if (k >= 0) {
                double q = Math.sqrt(k);
                int q2 = (int) q;
                if ((q2 * q2) == k) {
                    System.out.println(k);
                }
            } else {
                throw new ArithmeticException("Число " + k + " оказалось отрицательным, в операции нет смысла.");
            }
        }
    }
}

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

    static int numbers = 100;
    static Random random = new Random(new Date().getTime()); // для получения разных рандомных значений при каждом запуске

    /**
     * main method
     * @param args - array of strings
     * @throws ArithmeticException - происходит, если k < 0
     */
    public static void main(String[] args) throws ArithmeticException {
        for (int i = 0; i < numbers; i++) {
            int k = random.nextInt();

            // закомментированное решение, которое не совпадает с условием задачи (по нему требуется генерация искл-ия)
            // предпочитаю его, чтобы не было прерывания задачи в случае отрицательного числа
            /*try {
                double q = Math.sqrt(k);
                int q2 = (int) q;
                if ((q2 * q2) == k) {
                    System.out.println(k);
                } else {
                    System.out.println(k + "мда");
                }
            } catch (ArithmeticException e) {
                System.out.println("Число " + k + " оказалось отрицательным, в операции нет смысла");
            }*/

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

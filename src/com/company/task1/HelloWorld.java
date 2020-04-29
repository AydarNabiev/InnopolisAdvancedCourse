package com.company.task1;

/**
 * HelloWorld.
 * This is a program ”Hello, World!” which can throw "NullPointerException", "ArithmeticException"
 * and "NoSuchFieldException".
 *
 *  Version 1.0
 *
 * @author Aydar Nabiev
 */
public class HelloWorld {

    /**
     * main method
     * @param args - array of strings
     * @throws NullPointerException - случай, когда вызывается метод у null
     * @throws ArithmeticException - арифметическая ошибка
     * @throws NoSuchFieldException - случай, когда обращаемся несуществующему полю класса
     */
    public static void main(String[] args) throws NoSuchFieldException {
        String message = "Hello, world!";
        String emptyString = null;
        System.out.println(message);

        // в данном случае программа упадёт на первой ошибке, остальные не произойдут (по условию сказано, что должно
        // падать). нужно комментирование строки с первой произошедшей ошибкой, чтобы следующая проявилась

        emptyString.equals(message); // происходит NullPointerException, если не закомментировано

        int arithmeticExceptionNumber = 15/0; // происходит ArithmeticException, если не закомментировано

        throw new NoSuchFieldException("У класса нет указанного поля"); // происходит ArrayIndexOutOfBoundsException, если не закомментировано
    }
}
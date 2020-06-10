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
        try {
            emptyString.equals(message);
        } catch (NullPointerException e) {
            System.out.println("Произошёл NullPointerException");
        }
        try {
            int arithmeticExceptionNumber = 15/0;
        } catch (ArithmeticException e) {
            System.out.println("Произошёл ArithmeticException");
        }
        try {
            throw new NoSuchFieldException("У класса нет указанного поля");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Произошёл ArrayIndexOutOfBoundsException");
        }
    }
}
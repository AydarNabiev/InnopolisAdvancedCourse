package com.company.task1.subtask3;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Person.
 * This is a program that defines Person object
 * Sex parameter uses Sex enum (at the bottom) for possible values
 *
 *  Version 1.0
 *
 * @author Aydar Nabiev
 */
public class Person {
    private int age;
    private Sex sex;
    private String name;

    /**
     * main method
     * @param args - array of strings
     * @throws Exception - происходит, если сравниваемые объекты обладают одинаковыми именами и возрастами
     */
    public static void main(String[] args) throws Exception {
        ArrayList<Person> people = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            people.add(new Person(random.nextInt(100), Sex.values()[random.nextInt(2)], getRandomName()));
        }
        System.out.println(people);
        System.out.println("Список до сортировки");
        System.out.println("-------------------------------------------");
        ArrayList<Person> peopleCopy = new ArrayList<>(people);
        PersonBubbleSorter personBubbleSorter = new PersonBubbleSorter();
        long startTime = System.nanoTime();
        personBubbleSorter.sortPersonList(people);
        long endTime = System.nanoTime();
        long timeInMillis = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        System.out.println(people);
        System.out.println("Затраченное на сортировку пузырьком время : " + (timeInMillis) + " мс");
        System.out.println("-------------------------------------------");
        PersonInsertionSorter personInsertionSorter = new PersonInsertionSorter();
        startTime = System.nanoTime();
        personInsertionSorter.sortPersonList(peopleCopy);
        endTime = System.nanoTime();
        timeInMillis = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        System.out.println(peopleCopy);
        System.out.println("Затраченное на сортировку вставками время : " + (timeInMillis) + " мс");
    }

    /**
     * Method of getting random name for each Person
     */
    public static String getRandomName() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public Person(int age, Sex sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Sex.
     * Enum with values for two genders
     *
     * @author Aydar Nabiev
     */
    public enum Sex {
        MAN, WOMAN
    }
}
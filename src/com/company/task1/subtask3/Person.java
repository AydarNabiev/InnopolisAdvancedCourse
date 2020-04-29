package com.company.task1.subtask3;

import java.util.*;

public class Person {
    int age;
    Sex sex;
    String name;

    public static void main(String[] args) throws Exception {

        ArrayList<Person> people = new ArrayList<>();
        Random random = new Random(new Date().getTime()); // для получения разных рандомных значений при каждом запуске

        for (int i = 0; i < 10; i++) { //10000
            people.add(new Person(random.nextInt(100), Sex.values()[random.nextInt(2)], getRandomName()));
        }
        ArrayList<Person> peopleCopy = new ArrayList<>(people);
        //Collections.copy(peopleCopy, people);
        PersonBubbleComparator personBubbleComparator = new PersonBubbleComparator();
        personBubbleComparator.sortPersonList(people);
        System.out.println(people);
    }

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
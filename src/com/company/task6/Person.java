package com.company.task6;

public class Person {
    public String name;
    String gender;
    private int age;
    public boolean hasKids;
    public Car car;

    public Person(String name, String gender, int age, boolean hasKids, Car car) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.hasKids = hasKids;
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", hasKids=" + hasKids +
                ", car=" + car +
                '}';
    }
}
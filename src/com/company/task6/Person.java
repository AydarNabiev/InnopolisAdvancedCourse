package com.company.task6;

public class Person {
    public String name;
    String gender;
    private int age;
    public boolean hasKids;
    //public Car car;

    /*public Person(String name, String gender, int age, boolean hasKids, Car car) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.hasKids = hasKids;
        this.car = car;
    }*/

    public Person(String name, String gender, int age, boolean hasKids) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.hasKids = hasKids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isHasKids() {
        return hasKids;
    }

    public void setHasKids(boolean hasKids) {
        this.hasKids = hasKids;
    }

    /*public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }*/

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", hasKids=" + hasKids +
                //", car=" + car.toString() +
                '}';
    }
}
package com.company.task6;

public class Car {
    public String model;
    public int manufactureYear;
    public String color;

    public Car(String model, int manufactureYear, String color) {
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", manufactureYear=" + manufactureYear +
                ", color='" + color + '\'' +
                '}';
    }
}
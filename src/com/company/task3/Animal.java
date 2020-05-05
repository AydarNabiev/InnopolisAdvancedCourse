package com.company.task3;

public class Animal {
    private int id;
    private String alias;
    private Person owner;
    private int weight;

    public String getAlias() {
        return alias;
    }

    public Person getOwner() {
        return owner;
    }

    public int getWeight() {
        return weight;
    }

    public String getOwnerName() {
        return owner.getName();
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", кличка='" + alias + '\'' +
                ", имя владельца=" + owner.getName() +
                ", вес=" + weight +
                '}';
    }
}
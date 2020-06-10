package com.company.task3;

public class Animal {
    private int id;
    private String alias;
    private Person owner;
    private int weight;

    public Animal(int id, String alias, Person owner, int weight) {
        this.id = id;
        this.alias = alias;
        this.owner = owner;
        this.weight = weight;
    }

    public int getId() { return id; }

    public String getAlias() {
        return alias;
    }

    public Person getOwner() {
        return owner;
    }

    public int getWeight() {
        return weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
package com.company.task3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PetsRecords {
    private List<Animal> petsInDatabase;

    public void addAnimal(Animal animal) {
        if (petsInDatabase.contains(animal)) {
            try {
                throw new Exception("Питомец " + animal.toString() + " уже присутствует в картотеке.");
            } catch (Exception e) {
                System.out.println("Пожалуйста, добавьте другого питомца.");
            }
        } else {
            petsInDatabase.add(animal);

        }
    }

    public Animal searchAnimal(Animal animal) {
        if (petsInDatabase.contains(animal)) {
            return petsInDatabase.get(petsInDatabase.indexOf(animal));
        } else {
            System.out.println("Питомца " + animal + " нет в нашей картотеке.");
            return null;
        }
    }

    public Animal changeAnimal(int animalId) {
        return petsInDatabase.get(animalId);
    }

    public void printSortedPets() {
        ArrayList<Animal> petsSortedForPrint = new ArrayList<>(petsInDatabase);
        Collections.sort(petsSortedForPrint, Comparator.comparing(Animal::getOwnerName)
                .thenComparing(Animal::getAlias)
                .thenComparing(Animal::getWeight));
        System.out.println(petsSortedForPrint);
    }
}
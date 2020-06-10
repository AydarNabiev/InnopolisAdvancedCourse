package com.company.task3;

import java.util.*;

public class PetsRecords {
    private Set<Animal> petsInDatabase = new HashSet<>();

    public Set<Animal> getPetsInDatabase() {
        return petsInDatabase;
    }

    public void addAnimal(Animal animal) {

        if (!petsInDatabase.add(animal)) {
            System.out.println("Питомец " + animal + " уже присутствует в картотеке");
        }
    }

    public void searchAnimal(String alias) {
        Animal foundAnimal = petsInDatabase.stream()
                .filter(animal -> animal.getAlias().equals(alias))
                .findFirst()
                .orElse(null);
        System.out.println("По кличке " + alias + " нашли питомца: " + foundAnimal);
    }

    public void changeAnimal(Animal animal) {
        int animalId = animal.getId();
        Animal foundAnimal = petsInDatabase.stream()
                .filter(a -> a.getId() == (animalId))
                .findFirst()
                .orElse(null);
        if (foundAnimal != null) {
            petsInDatabase.remove(foundAnimal);
        }
        petsInDatabase.add(animal);
    }

    public void printSortedPets() {
        ArrayList<Animal> petsSortedForPrint = new ArrayList<>(petsInDatabase);
        Collections.sort(petsSortedForPrint, Comparator.comparing(Animal::getOwnerName)
                .thenComparing(Animal::getAlias)
                .thenComparing(Animal::getWeight));
        System.out.println(petsSortedForPrint);
    }
}
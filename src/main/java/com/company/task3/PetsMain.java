package com.company.task3;

public class PetsMain {
    public static void main(String[] args) {
        Person owner1 = new Person("Айдар", 27, "Male");
        Person owner2 = new Person("Игорь", 20, "Male");
        Person owner3 = new Person("Полина", 25, "Female");
        Animal pet1 = new Animal(1, "Боня", owner1, 10);
        Animal pet2 = new Animal(2, "Рита", owner2, 8);
        Animal pet3 = new Animal(3, "Рекс", owner3, 12);

        PetsRecords petsRecords = new PetsRecords();
        petsRecords.addAnimal(pet1);
        petsRecords.addAnimal(pet2);
        petsRecords.addAnimal(pet3);
        System.out.println("Картотека питомцев после добавления");
        System.out.println(petsRecords.getPetsInDatabase());
        System.out.println("--------------------------------------");
        System.out.println("Отсортированная копия картотеки");
        petsRecords.printSortedPets();
        System.out.println("--------------------------------------");
        System.out.println("Попытка добавить животного, который уже есть в картотеке");
        petsRecords.addAnimal(pet2);
        System.out.println("--------------------------------------");
        petsRecords.searchAnimal("Боня");
        petsRecords.searchAnimal("Random");
        System.out.println("--------------------------------------");
        Person owner4 = new Person("Новый владелец", 27, "Male");
        Animal pet4 = new Animal(1, "Питомец вместо Бони", owner4, 100);
        System.out.println("Проверка изменения питомца");
        petsRecords.changeAnimal(pet4);
        System.out.println(petsRecords.getPetsInDatabase());
        System.out.println("--------------------------------------");
    }
}
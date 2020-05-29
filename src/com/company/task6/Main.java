package com.company.task6;

import java.io.File;

public class Main {
    final static String PATH = "src\\com\\company\\task6\\objectData\\";

    public static void main(String[] args) {
        File folder = new File(PATH);
        folder.mkdir();
        for (File folderFiles: folder.listFiles()) {
            folderFiles.delete();
        }
        SerealizerDeserializer serealizerDeserializer = new SerealizerDeserializer();
        Car car = new Car("Ford", 2000, "Green");
        Person man = new Person("Ivan", "Male", 32, false, car);
        System.out.println(man.toString());
        String manFile = PATH + "man.txt";
        serealizerDeserializer.serialize(man, manFile);
        Person manCopy = (Person) serealizerDeserializer.deSerialize(manFile);
        System.out.println("================================");
        System.out.println("toString оригинального объекта");
        System.out.println(man.toString());
        System.out.println("================================");
        System.out.println("toString десериализованного объекта");
        System.out.println(manCopy.toString());
    }
}
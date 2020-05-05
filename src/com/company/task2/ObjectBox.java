package com.company.task2;

import java.util.ArrayList;

public class ObjectBox<O> {
    private ArrayList<O> listOfObjects;

    void addObject(O object) {
        listOfObjects.add(object);
    }

    void deleteObject(O object) {
        if (!listOfObjects.contains(object)) {
            listOfObjects.remove(object);
        }
    }

    void dump() {
        System.out.println(listOfObjects);
    }
}

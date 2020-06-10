package com.company.task2;

import java.util.ArrayList;

public class ObjectBox {
    private ArrayList<Object> listOfObjects;

    void addObject(Object object) {
        listOfObjects.add(object);
    }

    void deleteObject(Object object) {
        if (!listOfObjects.contains(object)) {
            listOfObjects.remove(object);
        }
    }

    void dump() {
        System.out.println(listOfObjects);
    }
}
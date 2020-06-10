package com.company.task1.subtask3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * PersonBubbleComparator.
 * This is a program that implements Comparator Interface
 * It uses bubble sorting
 *
 *  Version 1.0
 *
 * @author Aydar Nabiev
 */
public class PersonBubbleSorter implements SorterInterface, Comparator<Person> {

    /**
     * @param list - list of persons to sort
     * @throws Exception - происходит, если сравниваемые объекты обладают одинаковыми именами и возрастами
     */
    @Override
    public void sortPersonList(ArrayList<Person> list) throws Exception {
        for (int i = 0; i < list.size() - 1; i ++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                Person personJ = list.get(j);
                Person personNextToJ = list.get(j + 1);
                if (personJ.getName().equals(personNextToJ.getName()) && personJ.getAge() == personNextToJ.getAge()) {
                    throw new Exception("Имена и возрасты одинаковые - имя " + personJ.getName() + " и возраст " + personJ.getAge());
                }
                if (compare(personJ, personNextToJ) < 0) {
                    Collections.swap(list, j + 1, j);
                }
            }
        }
    }

    @Override
    public int compare(Person p1, Person p2) {
        int result;
        if (p1.getSex().equals(Person.Sex.WOMAN) && (p2.getSex().equals(Person.Sex.MAN))) {
            return -1;
        } else if (p1.getSex().equals(Person.Sex.MAN) && (p2.getSex().equals(Person.Sex.WOMAN))) {
            return 1;
        }
        result = p2.getAge() - p1.getAge();
        if (result != 0) {
            return result;
        }
        result = (p1.getName().compareTo(p2.getName()));
        if (result != 0) {
            return result;
        }
        return 0;
    }
}
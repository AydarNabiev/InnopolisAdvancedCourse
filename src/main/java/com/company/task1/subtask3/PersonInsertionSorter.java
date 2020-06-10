package com.company.task1.subtask3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * PersonInsertionComparator.
 * This is a program that implements Comparator Interface
 * It uses insertion sorting
 *
 *  Version 1.0
 *
 * @author Aydar Nabiev
 */
public class PersonInsertionSorter implements SorterInterface, Comparator<Person> {

    /**
     * @param list - list of persons to sort
     * @throws Exception - происходит, если сравниваемые объекты обладают одинаковыми именами и возрастами
     */
    @Override
    public void sortPersonList(ArrayList<Person> list) {
        for (int j = 1; j < list.size(); j++) {
            Person personJ = list.get(j);
            int i = j;
            while (i > 0 && compare(list.get(i - 1), personJ) < 0) {
                list.set(i, list.get(i - 1));
                i--;
            }
            list.set(i, personJ);
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
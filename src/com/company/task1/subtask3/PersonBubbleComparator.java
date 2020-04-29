package com.company.task1.subtask3;

import java.util.ArrayList;
import java.util.Collections;

public class PersonBubbleComparator implements ComparatorInterface {

    @Override
    public void sortPersonList(ArrayList<Person> list) throws Exception {
        sortByGender(list);
        sortByAge(list);
        sortByNames(list);
    }

    private void sortByGender(ArrayList<Person> list) throws Exception {
        for (int i = 0; i < list.size(); i ++) {
            for (int j = 0; j < list.size() - j; j++) {
                Person personJ = list.get(j);
                Person personNextToJ = list.get(j + 1);
                if (personJ.getName().equals(personNextToJ.getName()) && personJ.getAge() == personNextToJ.getAge()) {
                    throw new Exception("Имена и возрасты одинаковые - имя " + personJ.getName() + " и возраст " + personJ.getAge());
                }
                if (personJ.getSex().equals(Person.Sex.WOMAN) && (personNextToJ.getSex().equals(Person.Sex.MAN))) {
                    Collections.swap(list, j + 1, j);
                }
            }
        }

    }

    private void sortByAge(ArrayList<Person> list) {
        for (int i = 0; i < list.size(); i ++) {
            for (int j = 0; j < list.size() - j; j++) {
                if (list.get(j).getAge() < list.get(i).getAge()) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }

    }


    private void sortByNames(ArrayList<Person> list) {
        for (int i = 0; i < list.size(); i ++) {
            for (int j = 0; j < list.size() - j; j++) {
                if (list.get(j).getName().compareTo(list.get(j + 1).getName()) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }
}
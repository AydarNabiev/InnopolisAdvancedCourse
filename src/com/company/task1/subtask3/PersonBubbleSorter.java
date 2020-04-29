package com.company.task1.subtask3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * PersonBubbleComparator.
 * This is a program that implements ComparatorInterface
 * It uses bubble sorting
 *
 *  Version 1.0
 *
 * @author Aydar Nabiev
 */
public class PersonBubbleSorter implements SorterInterface {

    /**
     * @param list - list of persons to sort
     * @throws Exception - происходит, если сравниваемые объекты обладают одинаковыми именами и возрастами
     */
    @Override
    public void sortPersonList(ArrayList<Person> list) throws Exception {
        sortByGender(list);
        sortByAge(list);
        sortByNames(list);
        // неправильный путь, так как по нему правильно отсортировано будет лишь по последнему аргументу (имени) TODO разобраться
    }

    /**
     * Sorts by gender (Sex parameter)
     * @param list - list of persons to sort
     * @throws Exception - происходит, если сравниваемые объекты обладают одинаковыми именами и возрастами
     * Не добавлен в последующие методы, потому что в этом нет смысла
     */
    private void sortByGender(ArrayList<Person> list) throws Exception {
        for (int i = 0; i < list.size() - 1; i ++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
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

    /**
     * * Sorts by age
     * @param list - list of persons to sort
     */
    private void sortByAge(ArrayList<Person> list) {
        for (int i = 0; i < list.size() - 1; i ++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getAge() < list.get(i).getAge()) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }

    }

    /**
     * Sorts by name (using compareTo method)
     * @param list - list of persons to sort
     */
    private void sortByNames(ArrayList<Person> list) {
        for (int i = 0; i < list.size() - 1; i ++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).getName().compareTo(list.get(j + 1).getName()) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }
}
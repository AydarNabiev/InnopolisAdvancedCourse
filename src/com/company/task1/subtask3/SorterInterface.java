package com.company.task1.subtask3;

import java.util.ArrayList;

/**
 * SorterInterface.
 * This is an interface for comparison classes (with two different approaches)
 *
 *  Version 1.0
 *
 * @author Aydar Nabiev
 */
public interface SorterInterface {

    /**
     * @param list - list of persons to sort
     * @throws Exception - происходит, если сравниваемые объекты обладают одинаковыми именами и возрастами
     */
    void sortPersonList(ArrayList<Person> list) throws Exception;
}
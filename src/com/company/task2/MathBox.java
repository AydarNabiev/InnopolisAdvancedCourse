package com.company.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MathBox<N extends Number> extends ObjectBox {
    private List<N> mathboxList;

    public MathBox(Number[] numbers) {
        this.mathboxList = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            this.mathboxList.add((N) numbers[i]);
        }
    }

    public N summator() {
        N result = null;
        for (int i = 0; i < mathboxList.size(); i++) {
            result = result + mathboxList.get(i);
        }
        return null;
    }

    public void splitter(Number splitter) {
        for (int i = 0; i < mathboxList.size(); i++) {
            mathboxList.get(i) / splitter;
        }
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "mathboxList=" + mathboxList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(mathboxList, mathBox.mathboxList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mathboxList);
    }
}
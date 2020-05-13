package com.company.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MathBox extends ObjectBox {
    private List<Number> mathboxList = new ArrayList<>();

    public MathBox(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            this.mathboxList.add(numbers[i]);
        }
    }

    public Number summator() {
        double result = 0d;
        for (int i = 0; i < mathboxList.size(); i++) {
            result += (Integer) mathboxList.get(i);
        }
        return result;
    }

    public void splitter(int splitter) {
        for (int i = 0; i < mathboxList.size(); i++) {
            mathboxList.set(i, ((Double) mathboxList.get(i) / splitter));
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
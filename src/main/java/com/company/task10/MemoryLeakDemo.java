package com.company.task10;

public class MemoryLeakDemo {

    public static void main(String[] args) throws Exception {
        MemoryLeakDemo memoryLeakDemo = new MemoryLeakDemo();
        memoryLeakDemo.createArrays();
    }

    public void createArrays() {
        int arraySize = 20;
        while (true) {
            System.out.println("Available memory (in bytes): " + Runtime.getRuntime().freeMemory());
            int[] fillMemory = new int[arraySize];
            arraySize = arraySize * 5;
        }
    }

}
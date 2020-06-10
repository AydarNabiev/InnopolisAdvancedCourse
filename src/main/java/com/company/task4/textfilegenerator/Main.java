package com.company.task4.textfilegenerator;

import java.io.File;
import java.io.IOException;

import static com.company.task4.textfilegenerator.TextFileGenerator.RANDOM;
import static com.company.task4.textfilegenerator.TextFileGenerator.getFiles;
import static com.company.task4.textfilegenerator.WordGenerator.getWordPool;

public class Main {
    final static String PATH = "src\\main\\java\\com\\company\\task4\\textfilegenerator\\files\\";

    public static void main(String[] args) throws IOException {
        File file = new File(PATH);
        file.mkdir();
        for (File folderFiles: file.listFiles()) {
            folderFiles.delete();
        }
        int probability = 10;
        String[] words = getWordPool(RANDOM.nextInt(14) + 1);
        getFiles(PATH, 5, 1000, words, probability);
    }
}

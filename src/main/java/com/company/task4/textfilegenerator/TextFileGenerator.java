package com.company.task4.textfilegenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static com.company.task4.textfilegenerator.ParagraphGenerator.getParagraph;
import static com.company.task4.textfilegenerator.WordGenerator.wordGenerator;

public class TextFileGenerator {
    final static Random RANDOM = new Random();
    final static String[] ENDOFSENTENCE = {".", "!", "?"};

    public static void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        for (int i = 1; i <= n; i++) {
            File file = new File(path + wordGenerator(RANDOM.nextInt(14) + 1) + ".txt");
            String text = prepareTextForFile(size, words, probability);
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(text);
                fileWriter.flush();
                System.out.println(text);
                System.out.println("===================================");
            } catch (FileNotFoundException e) {
                System.out.println("Не найден файл " + file.getName());
            }
        }
    }

    static String prepareTextForFile(int size, String[] words, int probability) {
        String textForFile;
        textForFile = getParagraph(words, 10, probability) + "\n";
        while (textForFile.length() <= size - 1) {
            textForFile = textForFile + getParagraph(words, 10, probability) + "\n";
        }
        textForFile = textForFile.substring(0, size - 1) + ENDOFSENTENCE[RANDOM.nextInt(ENDOFSENTENCE.length)];
        return textForFile;
    }
}
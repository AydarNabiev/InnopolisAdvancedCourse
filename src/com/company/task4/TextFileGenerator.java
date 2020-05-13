package com.company.task4;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Random;

public class TextFileGenerator {
    final static Random random = new Random();

    public static void main(String[] args) throws IOException {
        String path = "src\\com\\company\\task4\\textfilegeneratorfiles";
        int probability = 10;
        String[] words = wordsGenerator(8);
        getFiles(path, 2, 50, words, probability);
    }

    public static String[] wordsGenerator(int n2) {
        String[] words = null;
        if ((n2 >= 1) && (n2 <= 15)) {
            words = new String[random.nextInt(1000)];
            for (int i = 0; i < words.length; i++) {
                words[i] = wordGenerator(n2);
            }
        } else {
            System.out.println("Число n2 находится вне требуемого диапазона!");
        }
        return words;
    }

    public static String wordGenerator(int n2) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(n2);
        for (int i = 0; i < n2; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        if ((n > 0) && (n <= 15)) {
            for (int i = 0; i <= n; n++) {
                File file = new File(wordGenerator(6));
                file.createNewFile();
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.setLength(n);
                randomAccessFile.close();
            }
        } else {
            System.out.println("Число n1 находится вне требуемого диапазона!");
        }
    }
}

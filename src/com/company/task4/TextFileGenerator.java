package com.company.task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TextFileGenerator {
    final static Random random = new Random();
    final static String[] endOfSentence = {".", "!", "?"};
    final static String path = "src\\com\\company\\task4\\textfilegeneratorfiles\\";

    public static void main(String[] args) throws IOException {
        File file = new File(path);
        file.mkdir();
        int probability = 10;
        String[] words = getWordPool(8);
        getFiles(path, 5, 1000, words, probability);
    }

    public static void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        if ((n > 0) && (n <= 15)) {
            for (int i = 1; i <= n; i++) {
                File file = new File(path + wordGenerator(5) + ".txt");
                String text = prepareTextForFile(size, words, probability);
                try (FileWriter fileWriter = new FileWriter(file)) {
                    fileWriter.write(text);
                    fileWriter.flush();
                    System.out.println(text);
                    System.out.println("===================================");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Число n1 находится вне требуемого диапазона!");
        }
    }

    public static String[] getWordPool(int n2) {
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
        StringBuilder buffer = new StringBuilder(n2);
        for (int i = 0; i < n2; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    private static String prepareTextForFile(int size, String[] words, int probability) {
        String textForFile;
        textForFile = getParagraph(words, 10, probability) + "\n";
        while (textForFile.length() <= size - 1) {
            textForFile = textForFile + getParagraph(words, 10, probability) + "\n";
        }
        textForFile = textForFile.substring(0, size - 1) + endOfSentence[random.nextInt(endOfSentence.length)];
        return textForFile;
    }

    private static String getParagraph(String[] words, int numberOfSentencesInOneParagraph, int probability) {
        String paragraph;
        paragraph = "\t";
        for (int i = 0; i <= random.nextInt(numberOfSentencesInOneParagraph); i ++) {
            paragraph = paragraph + getSentence(words, 10, probability);
        }
        return paragraph;
    }

    private static String getSentence(String[] words, int numberOfWordsInOneSentence, int probability) {
        String sentence;
        String firstWord = getWordFromArrayOrRandom(words, probability);
        sentence = firstWord.substring(0, 1).toUpperCase() + firstWord.substring(1);
        for (int i = 0; i <= random.nextInt(numberOfWordsInOneSentence) - 1; i++) {
            sentence = sentence + " " + getWordFromArrayOrRandom(words, probability);
        }
        sentence = sentence + endOfSentence[random.nextInt(endOfSentence.length)] + " ";
        return sentence;
    }

    private static String getWordFromArrayOrRandom(String[] words, int probability) {
        String word;
        int val = random.nextInt(probability) + 1;
        if (val == 1) {
            word = words[random.nextInt(words.length)];
        } else {
            word = wordGenerator(10);
        }
        return word;
    }
}
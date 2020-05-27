package com.company.task4.textfilegenerator;

import static com.company.task4.textfilegenerator.TextFileGenerator.RANDOM;

public class WordGenerator {

    static String[] getWordPool(int n2) {
        String[] words = null;
        if ((n2 >= 1) && (n2 <= 15)) {
            words = new String[RANDOM.nextInt(1000)];
            for (int i = 0; i < words.length; i++) {
                words[i] = wordGenerator(n2);
            }
        } else {
            System.out.println("Число n2 находится вне требуемого диапазона!");
        }
        return words;
    }

    static String wordGenerator(int n2) {
        StringBuilder buffer = new StringBuilder(n2);
        RANDOM
                .ints(97, 122)
                .limit(RANDOM.nextInt(14) + 1) //
                .boxed()
                .map(Character::toChars)
                .forEach(buffer::append);
        return buffer.toString();
    }

    static String getWordFromArrayOrRandom(String[] words, int probability) {
        String word;
        int val = RANDOM.nextInt(probability) + 1;
        if (val == 1) {
            word = words[RANDOM.nextInt(words.length)];
        } else {
            word = wordGenerator(RANDOM.nextInt(14) + 1);
        }
        return word;
    }
}

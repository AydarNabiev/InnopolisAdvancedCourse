package com.company.task4.textfilegenerator;

import static com.company.task4.textfilegenerator.TextFileGenerator.ENDOFSENTENCE;
import static com.company.task4.textfilegenerator.TextFileGenerator.RANDOM;
import static com.company.task4.textfilegenerator.WordGenerator.getWordFromArrayOrRandom;

public class SentenceGenerator {

    public static String getSentence(String[] words, int numberOfWordsInOneSentence, int probability) {
        String sentence;
        String firstWord = getWordFromArrayOrRandom(words, probability);
        sentence = firstWord.substring(0, 1).toUpperCase() + firstWord.substring(1);
        for (int i = 0; i <= RANDOM.nextInt(numberOfWordsInOneSentence) - 1; i++) {
            sentence = sentence + " " + getWordFromArrayOrRandom(words, probability);
        }
        sentence = sentence + ENDOFSENTENCE[RANDOM.nextInt(ENDOFSENTENCE.length)] + " ";
        return sentence;
    }
}

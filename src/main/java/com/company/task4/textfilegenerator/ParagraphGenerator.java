package com.company.task4.textfilegenerator;

import static com.company.task4.textfilegenerator.SentenceGenerator.getSentence;
import static com.company.task4.textfilegenerator.TextFileGenerator.RANDOM;

public class ParagraphGenerator {

    static String getParagraph(String[] words, int numberOfSentencesInOneParagraph, int probability) {
        String paragraph;
        paragraph = "\t";
        for (int i = 0; i <= RANDOM.nextInt(numberOfSentencesInOneParagraph); i ++) {
            paragraph = paragraph + getSentence(words, 10, probability);
        }
        return paragraph;
    }
}

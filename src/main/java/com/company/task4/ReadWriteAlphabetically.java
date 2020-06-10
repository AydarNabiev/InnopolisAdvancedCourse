package com.company.task4;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ReadWriteAlphabetically {

    public static void main(String[] args) {
        File input = new File("source.txt");
        File output = new File("output.txt");
        Set<String> allWords = new HashSet<>();
        String line;
        try (InputStream fileInputStream = new FileInputStream(input);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             FileWriter fileWriter = new FileWriter(output)) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] words = line.split(" ");
                allWords.addAll(Arrays.asList(words));
            }
            List<String> wordsToFile = new ArrayList<>(allWords);
            wordsToFile.sort(String::compareToIgnoreCase);
            for (String string: wordsToFile) {
                fileWriter.write(string + System.lineSeparator());
            }
            System.out.println(wordsToFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Файл не найден!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка потока ввода вывода!");
        }
    }
}
package com.company.task7;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    private List<String> readSource() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path sourcePatch = Paths.get("src/main/java/lesson09/SomeClass.java");
        List<String> someClassSourceList = Files.readAllLines(sourcePatch);

        String doWorkHeader = someClassSourceList.stream()
                .filter(s -> s.contains("public void doWork()"))
                .collect(Collectors.joining());

        int doWorkHeaderIndex = someClassSourceList.lastIndexOf(doWorkHeader);

        while (true) {
            String line = reader.readLine();
            if (line == null || line.equals("")) break;
            someClassSourceList.add(++doWorkHeaderIndex, line);
        }

        return someClassSourceList;
    }

    private Path saveSource(List<String> someClassSourceList) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        Path sourcePath = Paths.get(tempDir, "SomeClass.java");
        Files.write(sourcePath, someClassSourceList);
        return sourcePath;
    }

    private Path compileSource(Path sourcePath) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourcePath.toFile().getAbsolutePath());
        return sourcePath.getParent().resolve("SomeClass.class");
    }

    public void runCustomClassLoader() throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, IOException {
        Solution solution = new Solution();
        Path someClassFilePath = solution.compileSource(solution.saveSource(solution.readSource()));
        CustomClassLoader classLoader = new CustomClassLoader();
        classLoader.setPath(someClassFilePath);
        Class<?> someClass = classLoader.loadClass("lesson09.SomeClass");
        Worker worker = (Worker) someClass.newInstance();
        worker.doWork();
    }
}
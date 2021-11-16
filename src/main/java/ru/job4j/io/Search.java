package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
//        validation(args);
        Path start = Paths.get(args[0]);
//        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

//    public static void validation(String[] data) throws IllegalArgumentException {
//        File directoryFile = new File(data[0]);
//        if (data.length != 2) {
//            throw new IllegalArgumentException("Введенные параметры не соответсвуют шаблону поиска");
//        }
//        if (!directoryFile.exists() && !directoryFile.isDirectory()) {
//            throw new IllegalArgumentException("Не верный параметр для указания папки для поиска");
//        }
//    }
}

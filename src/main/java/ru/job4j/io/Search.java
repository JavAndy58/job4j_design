package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

import static java.nio.file.Files.isDirectory;

public class Search {
    public static void main(String[] args) throws IOException {
        validation(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validation(String[] data) throws IllegalArgumentException {
        Path directoryPath = Paths.get(data[0]);
        if (data.length != 2) {
            throw new IllegalArgumentException("Введенные параметры не соответсвуют шаблону поиска");

        }
        if (data[0] == null || !isDirectory(directoryPath)) {
            throw new IllegalArgumentException("Не верный параметр для указания папки для поиска");
        }
        if (data[1] == null) {
            throw new IllegalArgumentException("Не верный параметр для указания расширения файла");
        }
    }
}

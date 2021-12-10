package ru.job4j.io.search;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFile {
    private static List<Path> searchResult = new ArrayList<>();
    private static final Pattern TEMPLATE_PATH_FOLDER = Pattern.compile("([A-Z|a-z]:\\\\[^*|\"<>?\\n]*)|(\\\\\\\\.*?\\\\.*)");
    private static final Pattern TEMPLATE_FILE_SEARCH = Pattern.compile("\\w");
    private static final Pattern TEMPLATE_SEARCH_TYPE = Pattern.compile("[a-z]");
    private static final Pattern TEMPLATE_SEARCH_RESULT = Pattern.compile("[a-zA-Z0-9\\.a-z]");

    public static void handle(ArgsName argsName) throws IOException {
        validation(argsName);
        Path startFolder = Paths.get(argsName.get("d"));
        if ((Objects.equals(argsName.get("t"), "name"))) {
            searchResult = Search.search(startFolder, p -> p.toFile().getName().equals(argsName.get("n")));
        }
        if ((Objects.equals(argsName.get("t"), "mask"))) {
            searchResult = Search.search(startFolder, p -> p.toFile().getName().endsWith(argsName.get("n").substring(1)));
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(argsName.get("o")))) {
            for (Path file : searchResult) {
                out.write(file.toString());
                out.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validation(ArgsName argsName) throws IllegalArgumentException {
        Matcher matcherPathFolder = TEMPLATE_PATH_FOLDER.matcher(argsName.get("d"));
        File fileFolder = new File(argsName.get("d"));
        if (!fileFolder.isDirectory() && !matcherPathFolder.find()) {
            throw  new IllegalArgumentException("Папка для поиска указана неверно");
        }
        Matcher matcherFileSearch = TEMPLATE_FILE_SEARCH.matcher(argsName.get("n"));
        if (!matcherFileSearch.find()) {
            throw new IllegalArgumentException("Параметры для поиска указаны не верно");
        }
        Matcher matcherSearchType = TEMPLATE_SEARCH_TYPE.matcher("t");
        if (!matcherSearchType.find()) {
            throw  new IllegalArgumentException("Тип поиска указан не верно");
        }
        Matcher matcherSearchResult = TEMPLATE_SEARCH_RESULT.matcher(argsName.get("o"));
        File fileSearchResult = new File(argsName.get("o"));
        if (!fileSearchResult.exists() && !matcherSearchResult.find()) {
            throw new IllegalArgumentException("Файл указан не верно");
        }
    }

    public static void main(String[] args) throws IOException {
        handle(ArgsName.of(args));
    }
}

package ru.job4j.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {

    private static Map<String, String> values = new HashMap<>();
    private static final Pattern TEMPLATE_FILE = Pattern.compile("[a-zA-Z0-9\\.a-z]");
    private static final Pattern TEMPLATE_DELIMITER = Pattern.compile("\\W");
    private static final Pattern TEMPLATE_OUT = Pattern.compile("\\w");
    private static final Pattern TEMPLATE_FILTER = Pattern.compile("([a-z],)+");

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        names.validation(args);
        return names;
    }

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) throws IllegalArgumentException {
        String[] arguments;
        for (String argument : args) {
            arguments = argument.split("=");
            values.put(arguments[0].substring(1), arguments[1]);
        }
    }
    private void validation(String[] arguments) throws IllegalArgumentException {
        if (arguments.length != 4) {
            throw new IllegalArgumentException("Введены не все необходимые данные для работы программы");
        }

        Matcher matcherFile = TEMPLATE_FILE.matcher(get("path"));
        File file = new File(get("path"));
        if (!file.exists() && !file.isDirectory() && !matcherFile.find()) {
            throw new IllegalArgumentException("Файл указан не правильно или не существует");
        }
        Matcher matcherDelimited = TEMPLATE_DELIMITER.matcher(get("delimiter").substring(0, 1));
        if (!matcherDelimited.find()) {
            throw new IllegalArgumentException("Делитель указан не правильно");
        }
        Matcher matcherOut = TEMPLATE_OUT.matcher(get("out"));
        File fileOut = new File(get("out"));
        if (!matcherOut.find() && (!fileOut.exists() || get("out").equals("stdout"))) {
            throw new IllegalArgumentException("Файл для вывода или stdout указаны не правильно");
        }
        Matcher matcherFilter = TEMPLATE_FILTER.matcher(get("filter"));
        if (!matcherFilter.find()) {
            throw new IllegalArgumentException("Фильтр указан не правильно");
        }
    }
}

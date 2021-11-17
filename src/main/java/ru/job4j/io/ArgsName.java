package ru.job4j.io;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {

    private static Map<String, String> values = new HashMap<>();
    private static final Pattern TEMPLATE_FOLDER = Pattern.compile("-[a-z]+=\\w");
    private static final Pattern TEMPLATE_EXTENSION = Pattern.compile("[.*\\.a-z]");
    private static final Pattern TEMPLATE_FILE = Pattern.compile("[a-zA-Z0-9\\.a-z]");


    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) throws IllegalArgumentException {
        String[] arguments;
        if (args.length != 3) {
            throw new IllegalArgumentException("Не все данные введены");
        }
        for (String argument : args) {
            arguments = argument.split("=");
            values.put(arguments[0], arguments[1]);
        }
        Matcher matcherFolder = TEMPLATE_FOLDER.matcher(get("-d"));
        File fileDirectory = new File(get("-d"));
        if (!fileDirectory.isDirectory() && !matcherFolder.find()) {
            throw new IllegalArgumentException("Архивируемая директория указана не правильно или не существует");
        }
        Matcher matcherExtension = TEMPLATE_EXTENSION.matcher(get("-e").substring(1));
        if (!matcherExtension.find()) {
            throw new IllegalArgumentException("Неархивируемые файлы указаны не правильно");
        }
        Matcher matcherFile = TEMPLATE_FILE.matcher(get("-o"));
        if (!matcherFile.find()) {
            throw new IllegalArgumentException("Имя файла архива указано не правильно");
        }
    }

//    public static void main(String[] args) {
//        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
//        System.out.println(jvm.get("Xmx"));
//
//        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
//        System.out.println(zip.get("out"));
//    }
}

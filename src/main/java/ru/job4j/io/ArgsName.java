package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();
    private static final Pattern PAIR_TEMPLATE = Pattern.compile("-[a-zA-Z]+=\\w");

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) throws IllegalArgumentException {
        String[] line;

        if (args.length == 0) {
            throw new IllegalArgumentException("Данные не введены");
        }
        for (String str : args) {
            Matcher matcher = PAIR_TEMPLATE.matcher(str);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Данные не соответствует шаблону");
            }
            line = str.split("=");
            values.put(line[0].substring(1), line[1]);
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}

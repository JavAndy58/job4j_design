package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static List<Path> searchPaths = new ArrayList<>();
    public static List<File> searchFiles = new ArrayList<>();

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validation(String[] args) throws IllegalArgumentException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Не все данные введены");
        }
    }

    public static void main(String[] args) throws IOException {
        validation(args);
        ArgsName argsName = new ArgsName();
        ArgsName.of(args);
        Path rootPath = Paths.get(argsName.get("d"));
        searchPaths = Search.search(rootPath, nameFile -> !nameFile.toFile().getName()
                .endsWith(argsName.get("e").substring(1)));
        for (Path path : searchPaths) {
            searchFiles.add(path.toFile());
        }
        packFiles(searchFiles, new File(argsName.get("o")));
    }
}

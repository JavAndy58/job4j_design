package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public static List<Path> searchPaths = new ArrayList<>();
    public static List<File> searchFiles = new ArrayList<>();
    public static void packFiles(List<File> sources, File target) {
        for (File source : sources) {
            packSingleFile(source, target);
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName.of(args);
        ArgsName argsName = new ArgsName();
        Path rootPath = Paths.get(argsName.get("d"));
        searchPaths = Search.search(rootPath, nameFile -> !nameFile.toFile().getName().endsWith(argsName.get("e").substring(1)));
        for (Path path : searchPaths) {
            searchFiles.add(path.toFile());
        }
        packFiles(searchFiles, new File(argsName.get("o")));
    }
}

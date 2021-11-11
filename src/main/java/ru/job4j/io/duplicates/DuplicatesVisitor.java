package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Files.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<Path, FileProperty> tempMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (isRegularFile(file)) {
            FileProperty fileProperty = new FileProperty(size(file), file.getFileName().toString());
            if (tempMap.containsValue(fileProperty)) {
                System.out.println(file.toAbsolutePath());
            } else {
                tempMap.put(file, fileProperty);
            }
        }
        return super.visitFile(file, attrs);
    }

}
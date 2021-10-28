package ru.job4j.io;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class ResultFile {
    public static int[][] multiple(int size) {
        int[][] numbers = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                numbers[i][j] = (i + 1) * (j + 1);
            }
        }
        return numbers;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] arr = multiple(9);

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                   out.write((arr[i][j] + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

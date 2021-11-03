package ru.job4j.io;

import java.io.*;
import java.util.Objects;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
        PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            String line;
            String[] lines;
            String[] targets = new String[2];

            while ((line = in.readLine()) != null) {
                if (line.contains("400 ") || line.contains("500 ")) {
                    lines = line.split(" ");
                    int rsl = 0;




                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

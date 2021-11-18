package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> listPhrases = readPhrases();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            String inputText = bufferedReader.readLine();
            while (!inputText.equals(OUT)) {
//                System.out.println(inputText);


                if (!inputText.equals(STOP)) {
                    int randomIndex = (int) (Math.random() * listPhrases.size());
                    System.out.println(listPhrases.get(randomIndex));
                }

                inputText = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<String> readPhrases() {
        List<String> listPhrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(listPhrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listPhrases;
    }

    private void saveLog(List<String> log) {

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/history_dialogue.txt", "./data/bot_words.txt");
        cc.run();
    }
}

package com.quiz;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class Quiz {
    private final HashMap<String, List<Option>> questions = new HashMap<>();
    private int score;
    private boolean turnEnglish;
    private static final String PATH_RU = "src/main/resources/quiz20ru.csv";
    private static final String PATH_EN = "src/main/resources/quiz20en.csv";

    public Quiz() throws IOException {
        this.loadQuizQuestions();
    }

    public void setTurnEnglish(boolean english) throws IOException {
        this.turnEnglish = english;
        this.loadQuizQuestions();
    }

    public HashMap<String, List<Option>> getQuestions() {
        return this.questions;
    }

    public void startQuiz() throws IOException {
        this.score = 0;
        final Scanner scanner = new Scanner(System.in);
        this.selectLanguage();
        this.loadQuizQuestions();
        System.out.println(Communication.ENTER_YOUR_NAME.getWords(this.turnEnglish));
        System.out.println(scanner.next() + Communication.START.getWords(this.turnEnglish));
        for (Map.Entry<String, List<Option>> entry : this.getQuestions().entrySet()) {
            System.out.println(entry.getKey());
            Quiz.printOptions(entry.getValue());
            System.out.println(Communication.SELECT_NUMBER.getWords(this.turnEnglish));
            this.checkAnswer(checkInput(scanner.next()), entry.getValue());
        }
        StringBuilder sb = new StringBuilder(String.valueOf(Communication.SCORE.getWords(this.turnEnglish)))
                .append(this.score)
                .append(Communication.FROM.getWords(this.turnEnglish))
                .append(this.questions.size())
                .append(".");
        System.out.println(sb);
        scanner.close();
    }

    public void addQuestion(final String question, final List<Option> options) {
        this.questions.put(question, options);
    }

    private void selectLanguage() throws IOException {
        final String selectLanguage = "Если вы хотите пройти тест на русском, введите 1. If you want to take the test in English, type 2.";
        System.out.println(selectLanguage);
        final Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        if (!input.matches("[1-2]")) {
            selectLanguage();
        }
        this.setTurnEnglish(Integer.parseInt(input) == 2);
    }


    private static void printOptions(final List<Option> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println(i + 1 + ". ".concat(options.get(i).toString()));
        }
    }

    private int checkInput(final String input) {
        if (!input.matches("[1-5]")) {
            System.out.println(Communication.SELECT_NUMBER.getWords(this.turnEnglish));
            final Scanner scanner = new Scanner(System.in);
            return checkInput(scanner.next());
        } else {
            return Integer.parseInt(input);
        }
    }

    private void checkAnswer(final int answer, final List<Option> options) {
        if (options.get(answer - 1).isCorrect()) {
            this.score++;
        }
    }

    private void loadQuizQuestions() throws IOException {
        final File file = this.turnEnglish ? new File(PATH_EN) : new File(PATH_RU);
        this.questions.clear();
        final Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(new FileReader(file));
        for (CSVRecord record : records) {
            List<Option> options = new ArrayList<>();
            options.add(new Option(record.get(1), true));
            for (int i = 2; i < record.size(); i++) {
                options.add(new Option(record.get(i)));
            }
            Collections.shuffle(options);
            this.addQuestion(record.get(0), options);
        }
    }
}

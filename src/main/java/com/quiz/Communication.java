package com.quiz;

public enum Communication {
    FROM(" from ", " из "),
    ENTER_ANSWER("PLEASE ENTER A NUMBER BETWEEN 1 AND ", "Пожалуйста введите число от 1 до "),
    ENTER_YOUR_NAME("Enter your first and last name", "Введите свои имя и фамилию"),
    START(", Let's start the test", ", начинаем тест."),
    SELECT_NUMBER("Enter the number of the correct answer. ", "Введите номер ответа, который считаете правильным."),
    SCORE("You scored ", "Вы набрали ");

    private final String english;
    private final String russian;

    public String getWords(boolean turnEnglish){
        if (turnEnglish) {
            return this.english;
        }
        return this.russian;
    }

    Communication(String english, String russian) {
        this.english = english;
        this.russian = russian;
    }
}

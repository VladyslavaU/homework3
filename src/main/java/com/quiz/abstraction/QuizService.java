package com.quiz.abstraction;

import com.quiz.model.Option;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface QuizService {
    void startQuiz() throws IOException;
    HashMap<String, List<Option>> getQuestions();
}

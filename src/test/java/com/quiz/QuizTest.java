package com.quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class QuizTest {
   private Quiz quiz;

    @BeforeEach
    public void setUp() throws IOException {
        this.quiz = new Quiz();
    }

    @Test
    void getQuestionsNumberRussian(){
        assertEquals(quiz.getQuestions().size(), 14);
    }

    @Test
    void getQuestionsNumberEnglish() throws IOException {
        quiz.setTurnEnglish(true);
        assertEquals(quiz.getQuestions().size(), 15);
    }

    @Test
    void shouldHaveOneAndOnlyOneCorrectOptionInEachQuestion() {
        final Map<String, List<Option>> questions = quiz.getQuestions();
        for(Map.Entry<String,List<Option>> entry: questions.entrySet()){
            final List<Option> correctOption = entry.getValue().stream()
                    .filter(Option::isCorrect)
                    .collect(Collectors.toList());
            assertEquals(correctOption.size(), 1);
        }
    }
}

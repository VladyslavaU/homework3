import com.quiz.abstraction.QuizService;
import com.quiz.implementation.Quiz;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Application{
    public static void main(String[] args) throws IOException {
        final AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class);
        ctx.refresh();
        final QuizService quiz = ctx.getBean(Quiz.class);
        quiz.startQuiz();
    }
}

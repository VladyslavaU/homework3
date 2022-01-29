import com.quiz.abstraction.QuizService;
import com.quiz.implementation.Quiz;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Config {
    @Bean
    public QuizService quiz() throws IOException {
        return new Quiz();
    }
}


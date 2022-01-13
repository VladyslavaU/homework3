import com.quiz.Quiz;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class Config {
    @Bean
    public Quiz quiz() throws IOException {
        return new Quiz();
    }
}


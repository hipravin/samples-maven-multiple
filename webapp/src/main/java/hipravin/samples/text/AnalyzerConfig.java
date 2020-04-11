package hipravin.samples.text;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnalyzerConfig {

    @Bean
    TextAnalyzer textAnalyzer() {
        return new TextAnalyzerImpl();
    }
}

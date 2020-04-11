package hipravin.samples.text;

import java.util.Map;
import java.util.stream.Stream;

public interface TextAnalyzer {
    Map<String, Long> calculateWordFrequency(Stream<String> lines);
}

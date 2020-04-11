package hipravin.samples.text;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextAnalyzerImpl implements TextAnalyzer {
    private final Pattern UNICODE_WORD_PATTERN = Pattern.compile("(?U)\\w+");

    @Override
    public Map<String, Long> calculateWordFrequency(Stream<String> lines) {
        try(Stream<String> words = toWordsStream(lines)) {
            return words
                    .map(String::toLowerCase)
                    .collect(Collectors.toConcurrentMap(Function.identity(), s -> 1L, Long::sum));
        }
    }

    protected Stream<String> toWordsStream(Stream<String> lines) {
        return lines.flatMap(this::parseLine);
    }

    protected Stream<String> parseLine(String line) {
        List<String> words = new LinkedList<>();
        Matcher matcher = UNICODE_WORD_PATTERN.matcher(line);
        while (matcher.find()) {
            words.add(matcher.group(0));
        }

        return words.stream();
    }
}

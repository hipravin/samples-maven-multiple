package hipravin.samples.text;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextAnalyzerImplTest {
    TextAnalyzerImpl ta = new TextAnalyzerImpl();

    @Test
    void testFrequency() {
        Map<String, Long> freq = ta.calculateWordFrequency(Stream.of("a a a", "b a a", "a b c"));
        assertEquals(3, freq.size());
        assertEquals(6, freq.get("a"));
        assertEquals(2, freq.get("b"));
        assertEquals(1, freq.get("c"));
    }

    @RepeatedTest(100)
    void testFrequencyParallel() {
        int n = 10_000;
        List<String> lines = new LinkedList<>();
        for (int i = 0; i < n; i++) {
             lines.add("a a a");
             lines.add("b a a");
             lines.add("a b c");
        }

        Map<String, Long> freq = ta.calculateWordFrequency(lines.parallelStream());
        assertEquals(3, freq.size());
        assertEquals(6 * n, freq.get("a"));
        assertEquals(2 * n, freq.get("b"));
        assertEquals(n, freq.get("c"));
    }

    @Test
    void testToWordStream() {
        List<String> words = ta.toWordsStream(Stream.of("", " , ")).collect(Collectors.toList());
        assertTrue(words.isEmpty());

        words = ta.toWordsStream(Stream.of("Ц Ц sdfs, b ййй", " , Ц!")).collect(Collectors.toList());
        assertEquals(Arrays.asList("Ц", "Ц", "sdfs", "b", "ййй", "Ц"), words);
    }
}
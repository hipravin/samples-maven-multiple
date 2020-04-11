package hipravin.samples.text;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static List<Map.Entry<String, Long>> orderByValueDesc(Map<String, Long> frequencyMap) {
        Comparator<Map.Entry<String, Long>> byFreq = Map.Entry.comparingByValue(Comparator.reverseOrder());
        Comparator<Map.Entry<String, Long>> byFreqThenKey = byFreq.thenComparing(Map.Entry::getKey);

        return frequencyMap.entrySet().stream()
                .sorted(byFreqThenKey)
                .collect(Collectors.toList());
    }
}

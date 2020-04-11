package hipravin.samples.text;

import java.util.Map;

public class TextStatsDto {
    private Map<String, Long> frequency;

    public TextStatsDto() {
    }

    public TextStatsDto(Map<String, Long> frequency) {
        this.frequency = frequency;
    }

    public Map<String, Long> getFrequency() {
        return frequency;
    }

    public void setFrequency(Map<String, Long> frequency) {
        this.frequency = frequency;
    }
}

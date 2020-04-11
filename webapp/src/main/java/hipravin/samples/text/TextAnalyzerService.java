package hipravin.samples.text;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class TextAnalyzerService {
    private final TextAnalyzer textAnalyzer;

    public TextAnalyzerService(TextAnalyzer textAnalyzer) {
        this.textAnalyzer = textAnalyzer;
    }

    @PostMapping("/upload")
    public TextStatsDto handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Long> freqSorted = new LinkedHashMap<>();
        Utils.orderByValueDesc(textAnalyzer.calculateWordFrequency(lines(file))).forEach(me -> {
            freqSorted.put(me.getKey(), me.getValue());
        });

        return new TextStatsDto(freqSorted);
    }

    Stream<String> lines(MultipartFile file) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        return r.lines().onClose(() -> {
            try {
                r.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

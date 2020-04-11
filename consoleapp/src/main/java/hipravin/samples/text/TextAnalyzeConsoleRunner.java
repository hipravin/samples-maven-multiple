package hipravin.samples.text;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextAnalyzeConsoleRunner {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Provide path to file that should be analyzed");
            System.err.println("E.g. TextAnalyzeConsoleRunner data/dost.txt");
            return;
        }
        TextAnalyzer textAnalyzer = new TextAnalyzerImpl();

        try (Stream<String> lines = Files.lines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
            Utils.orderByValueDesc(textAnalyzer.calculateWordFrequency(lines))
                    .forEach(me -> {
                        System.out.println(me.getKey() + "\t" + me.getValue());
                    });
        }
    }
}

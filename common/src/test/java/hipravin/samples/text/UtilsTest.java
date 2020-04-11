package hipravin.samples.text;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void orderByValueDesc() {
        Map<String, Long> freq = Map.of("c", 3L, "b", 2L, "a", 2L);
        List<Map.Entry<String, Long>> sorted = Utils.orderByValueDesc(freq);

        assertEquals("c", sorted.get(0).getKey());
        assertEquals("a", sorted.get(1).getKey());
        assertEquals("b", sorted.get(2).getKey());
    }
}
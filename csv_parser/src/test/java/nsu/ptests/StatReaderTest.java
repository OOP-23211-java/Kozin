package nsu.ptests;

import java.nio.file.Path;

import nsu.parser.*;
import nsu.parser.io.*;
import nsu.parser.io.interfaces.IStatReader;
import java.io.FileNotFoundException;
import nsu.parser.exceptions.*;

import org.junit.jupiter.api.Test;
import com.google.common.collect.Multiset;
import static org.junit.jupiter.api.Assertions.*;

public class StatReaderTest {

    @Test
    void testCountWords() {
        assertEquals(10, new StatReader(Path.of("../txt/ten_words.txt")).getWordStatMultiset().size());
        assertEquals(0, new StatReader(Path.of("../txt/zero_words.txt")).getWordStatMultiset().size());
    }

    @Test
    void testCountWord() {
        assertEquals(3, new StatReader(Path.of("../txt/three_hello.txt")).getWordStatMultiset().count("hello"));
        assertEquals(0, new StatReader(Path.of("../txt/zero_why.txt")).getWordStatMultiset().count("why"));
    }

    @Test
    void testInputFile() {
        assertThrows(FileNotFoundException.class, () -> new StatReader(Path.of("")).getWordStatMultiset());
        assertThrows(FileNotFoundException.class, () -> new StatReader(Path.of("why")).getWordStatMultiset());
    }
    
}

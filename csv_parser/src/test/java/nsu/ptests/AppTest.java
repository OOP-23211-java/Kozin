package nsu.ptests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import nsu.parser.App;
import nsu.parser.io.*;


public class AppTest {
    
    @Test
    void testCountArgsApp() {
        assertThrows(IllegalArgumentException.class, () -> App.main(new String[]{"Hello"}));
    }

    @Test
    void testOutputFile() throws IOException {
        String inputFilePath = "src/test/java/nsu/txt/ten_words.txt";
        String outputFilePath = "src/test/java/nsu/txt/real_output.csv";
        String expectedFileOutputPath= "src/test/java/nsu/txt/expected_output.csv";

        App.main(new String[]{inputFilePath, outputFilePath});  

        byte[] expectedOutputFile = Files.readAllBytes(Path.of(expectedFileOutputPath));
        byte[] realOutputFile = Files.readAllBytes(Path.of(outputFilePath));

        assertArrayEquals(expectedOutputFile, realOutputFile);
    }
    
}

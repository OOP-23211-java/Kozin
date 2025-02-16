package nsu.parser.io;

import nsu.parser.exceptions.*;
import nsu.parser.interfaces.IStatReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
/**
 * Класс, подсчитывающий количество
 * слов во входном файле.
 * Основан на google Multiset.
 */
public class StatReader implements IStatReader {
    private final Path inputFilePath;
    private static final String WORD_REGEX = "\\p{Punct}";

    public StatReader(Path inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public Multiset<String> getWordStatMultiset() {
        checkInputFile();
        return readWordStatMultiset();
    }

    private void checkInputFile() {
        if (Files.notExists(inputFilePath) || Files.isDirectory(inputFilePath)) {
            throw new InvalidInputFileException("Can't open input file: " + inputFilePath);
        }
    }

    private Multiset<String> readWordStatMultiset() {
        Multiset<String> wordStatMultiset = HashMultiset.create();

        try (Scanner scan = new Scanner(Files.newBufferedReader(inputFilePath))) {
            while (scan.hasNext()) {
                String correctWord = scan.next().replaceAll(WORD_REGEX, "").toLowerCase();
                wordStatMultiset.add(correctWord);
            }

        } catch (Exception e) {
            throw new InvalidReadFromFileException("Can't read from input file: " + inputFilePath);
        }

        return wordStatMultiset;
    } 
}

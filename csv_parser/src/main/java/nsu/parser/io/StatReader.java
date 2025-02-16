package nsu.parser.io;

import nsu.parser.exceptions.*;
import nsu.parser.interfaces.IStatReader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class StatReader implements IStatReader {
    private final Path _inputFilePath;

    public StatReader(Path inputFilePath) {
        _inputFilePath = inputFilePath;
    }

    public Multiset<String> getWordStatMultiset() {
        checkInputFile();
        return readWordStatMultiset();
    }

    private void checkInputFile() {
        if (Files.notExists(_inputFilePath) || Files.isDirectory(_inputFilePath)) {
            throw new InvalidInputFileException("Can't open input file: " + _inputFilePath);
        }
    }

    private Multiset<String> readWordStatMultiset() {
        Multiset<String> wordStatMultiset = HashMultiset.create();

        try (Scanner scan = new Scanner(Files.newBufferedReader(_inputFilePath))) {
            while (scan.hasNext()) {
                String correctWord = scan.next().replaceAll("\\p{Punct}", "").toLowerCase();
                wordStatMultiset.add(correctWord);
            }

        } catch (Exception e) {
            throw new InvalidReadFromFileException("Can't read from input file: " + _inputFilePath);
        }

        return wordStatMultiset;
    } 
}

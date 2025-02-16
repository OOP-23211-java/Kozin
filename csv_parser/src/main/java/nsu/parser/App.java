package nsu.parser;

import nsu.parser.io.*;
import nsu.parser.interfaces.*;

import java.nio.file.Path;

import com.google.common.collect.Multiset;

public class App {
    public static void main(String[] args) { 
        try {
            ArgumentValidator.validate(args);
            convertToCSV(Path.of(args[0]), Path.of(args[1]));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void convertToCSV(Path inputFilePath, Path outputFilePath) {
        IStatReader statReader = new StatReader(inputFilePath);
        Multiset<String> wordStatMultiset = statReader.getWordStatMultiset();

        IWriter writer = new Writer(outputFilePath, wordStatMultiset);
        writer.writeCSV();
    }
}
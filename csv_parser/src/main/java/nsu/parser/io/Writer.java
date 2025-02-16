package nsu.parser.io;

import nsu.parser.interfaces.IWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import com.google.common.collect.Multiset;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Writer implements IWriter {
    private Path _outputFilePath;
    private Multiset<String> _wordStatMultiset;
    private Multimap<Integer, String> _sortedWordCountMap;

    public Writer(Path outputFilePath, Multiset<String> wordStatMultiset) {
        _outputFilePath = outputFilePath;
        _wordStatMultiset = wordStatMultiset;
    }

    public void writeCSV() {
        _sortedWordCountMap = getSortedMap();
        writeFromSortedMap();
    }

    private Multimap<Integer, String> getSortedMap() {
        Multimap<Integer, String> wordCountMap = ArrayListMultimap.create();

        for (String word : _wordStatMultiset.elementSet()) {
            wordCountMap.put(_wordStatMultiset.count(word), word);
        }
        
        return wordCountMap;
    }

    private void writeFromSortedMap() {
        try (BufferedWriter writer = Files.newBufferedWriter(_outputFilePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (var countAndWordPair : _sortedWordCountMap.entries()) {
                writer.write(countAndWordPair.getValue() + "\t" + countAndWordPair.getKey());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Can't open output file: " + _outputFilePath);
        }
    }
    
}

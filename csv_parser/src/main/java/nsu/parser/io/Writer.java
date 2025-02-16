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
/**
 * Класс, выполняющий сортировку по количеству
 * слов во входном файле, а после записывающий
 * в выходной файл в формате CSV.
 */
public class Writer implements IWriter {
    private Path outputFilePath;
    private Multiset<String> wordStatMultiset;
    private Multimap<Integer, String> sortedWordCountMap;

    public Writer(Path outputFilePath, Multiset<String> wordStatMultiset) {
        this.outputFilePath = outputFilePath;
        this.wordStatMultiset = wordStatMultiset;
    }

    public void writeCSV() {
        sortedWordCountMap = getSortedMap();
        writeFromSortedMap();
    }

    private Multimap<Integer, String> getSortedMap() {
        Multimap<Integer, String> wordCountMap = ArrayListMultimap.create();

        for (String word : wordStatMultiset.elementSet()) {
            wordCountMap.put(wordStatMultiset.count(word), word);
        }
        
        return wordCountMap;
    }

    private void writeFromSortedMap() {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (var countAndWordPair : sortedWordCountMap.entries()) {
                writer.write(countAndWordPair.getValue() + "\t" + countAndWordPair.getKey());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Can't open output file: " + outputFilePath);
        }
    }
    
}

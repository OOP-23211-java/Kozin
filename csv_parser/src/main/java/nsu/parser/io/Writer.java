package nsu.parser.io;

import nsu.parser.io.interfaces.IWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Comparator;

import com.google.common.collect.Multiset;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * Класс {@code Writer} выполняет сортировку слов по их количеству в тексте и записывает результат в выходной CSV-файл.
 * <p>Использует {@link Multimap} для хранения отсортированной информации, где ключом является количество вхождений слова, 
 * а значением — само слово.</p>
 *
 * <p><b>Основные функции:</b></p>
 * <ul>
 *     <li>Сортирует слова по количеству их вхождений в текст.</li>
 *     <li>Сохраняет отсортированные данные в CSV-файл с разделителем табуляции.</li>
 * </ul>
 *
 * <p><b>Пример использования:</b></p>
 * <pre>{@code
 * Path outputPath = Path.of("output.csv");
 * IWriter writer = new Writer(outputPath);
 * Multiset<String> wordStats = HashMultiset.create();
 * wordStats.add("example", 3);
 * wordStats.add("test", 5);
 * writer.writeCSV(wordStats); // Результат: test -> 5, example -> 3
 * }</pre>
 *
 * @see Multiset
 * @see Multimap
 * @see HashMultimap
 * @see IWriter
 */
public class Writer implements IWriter {
    private Path outputFilePath;

     /**
     * Конструктор {@code Writer}, инициализирующий путь для записи выходного файла.
     *
     * @param outputFilePath путь к выходному файлу CSV.
     */
    public Writer(String outputFilePath) {
        this.outputFilePath = Path.of(outputFilePath);
    }

    /**
     * Сортирует слова по количеству их вхождений и записывает результаты в CSV-файл.
     *
     * @param wordStatMultiset {@link Multiset} с количеством каждого слова.
     */
    @Override
    public void writeCSV(Multiset<String> wordStatMultiset) {
        var sortedWordCountMap = getSortedMap(wordStatMultiset);
        writeFromSortedMap(sortedWordCountMap);
    }

    private Multimap<Integer, String> getSortedMap(Multiset<String> wordStatMultiset) {
        Multimap<Integer, String> sortedMap = HashMultimap.create();

        wordStatMultiset.entrySet().stream()
        .sorted(Comparator.comparingInt((Multiset.Entry<String> entry) -> entry.getCount()))
        .forEach(entry -> sortedMap.put(entry.getCount(), entry.getElement()));

        return sortedMap;
    }

    private void writeFromSortedMap(Multimap<Integer, String> sortedWordCountMap) {
        try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardOpenOption.CREATE)) {
            for (var countAndWordPair : sortedWordCountMap.entries()) {
                writer.write(countAndWordPair.getValue() + "\t" + countAndWordPair.getKey());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Can't open/create output file: " + outputFilePath);
        }
    }
    
}

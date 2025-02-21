package nsu.parser.io;

import nsu.parser.exceptions.*;
import nsu.parser.io.interfaces.IStatReader;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Класс {@code StatReader} предназначен для подсчёта количества слов во входном текстовом файле.
 * Использует структуру данных {@link Multiset} из библиотеки Google Guava для хранения статистики слов.
 * Каждое слово обрабатывается для удаления знаков препинания и приведения к нижнему регистру.
 *
 * <p><b>Особенности:</b></p>
 * <ul>
 *     <li>Проверяет существование входного файла перед чтением.</li>
 *     <li>Считанные слова приводятся к нижнему регистру и очищаются от знаков препинания.</li>
 *     <li>Результатом работы является {@link Multiset}, где каждому слову соответствует его количество в тексте.</li>
 * </ul>
 *
 * <p><b>Пример использования:</b></p>
 * <pre>{@code
 * Path inputPath = Path.of("input.txt");
 * IStatReader reader = new StatReader(inputPath);
 * Multiset<String> wordStats = reader.getWordStatMultiset();
 * wordStats.forEachEntry((word, count) -> System.out.println(word + ": " + count));
 * }</pre>
 *
 * @see IStatReader
 * @see Multiset
 * @see HashMultiset
 */
public class StatReader implements IStatReader {
    private final Path inputFilePath;
    private static final String WORD_REGEX = "\\p{Punct}";

    /**
     * Создает новый экземпляр {@code StatReader} с указанным путем к входному файлу.
     *
     * @param inputFilePath путь к текстовому файлу для чтения слов.
     */
    public StatReader(Path inputFilePath) {
        this.inputFilePath = inputFilePath;        
    }

    /**
     * Возвращает {@link Multiset}, содержащий статистику по словам из входного файла.
     * <ul>
     *     <li>Проверяет существование входного файла.</li>
     *     <li>Читает файл построчно, удаляет знаки препинания, приводит слова к нижнему регистру и подсчитывает их количество.</li>
     * </ul>
     *
     * @return {@link Multiset} со словами и их количеством.
     * @throws RuntimeException если файл не существует или произошла ошибка чтения.
     */
    @Override
    public Multiset<String> getWordStatMultiset() {
        try {
            checkInputFile();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        return readWordStatMultiset();
    }

    private void checkInputFile() throws FileNotFoundException {
        if (Files.notExists(inputFilePath) || Files.isDirectory(inputFilePath)) {
            throw new FileNotFoundException("Failed to open file " + inputFilePath);
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
            throw new InvalidReadFromFileException("Failed reading from file: " + inputFilePath);
        }

        return wordStatMultiset;
    }

}

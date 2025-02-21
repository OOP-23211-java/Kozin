package nsu.parser;

import nsu.parser.io.*;
import nsu.parser.validators.ArgumentValidator;
import nsu.parser.io.interfaces.*;

import java.nio.file.Path;

import com.google.common.collect.Multiset;

/**
 * Класс {@code App} является точкой входа в программу для конвертации текстового файла (TXT) 
 * в CSV формат. Программа читает входной файл, подсчитывает количество слов и записывает 
 * полученную статистику в выходной CSV файл.
 *
 * <p><b>Основные шаги работы программы:</b></p>
 * <ol>
 *     <li>Валидация аргументов командной строки с помощью {@link ArgumentValidator}.
 *         Аргументами являются пути к входному и выходному файлам.</li>
 *     <li>Считывание статистики слов из входного файла с использованием {@link IStatReader}.</li>
 *     <li>Запись отсортированной статистики слов в CSV файл с использованием {@link IWriter}.</li>
 * </ol>
 *
 * <p><b>Формат аргументов:</b></p>
 * <pre>
 *     java App input.txt output.csv
 * </pre>
 * <ul>
 *     <li><b>input.txt</b> — путь к входному текстовому файлу.</li>
 *     <li><b>output.csv</b> — путь к выходному CSV файлу.</li>
 * </ul>
 *
 * <p><b>Обработка ошибок:</b></p>
 * Если валидация аргументов или процесс чтения/записи завершается неудачно, в консоль выводится 
 * сообщение об ошибке с описанием проблемы.
 *
 * @see ArgumentValidator
 * @see IStatReader
 * @see IWriter
 */
public class App {
    public static void main(String[] args) { 
        try {
            var validArgs = ArgumentValidator.validate(args);
            convertToCSV(Path.of(validArgs.inputFilePath()), Path.of(validArgs.outputFilePath()));
        } catch (RuntimeException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void convertToCSV(Path inputFilePath, Path outputFilePath) {
        IStatReader statReader = new StatReader(inputFilePath);
        Multiset<String> wordStatMultiset = statReader.getWordStatMultiset();

        IWriter writer = new Writer(outputFilePath);
        writer.writeCSV(wordStatMultiset);

    }
    
}
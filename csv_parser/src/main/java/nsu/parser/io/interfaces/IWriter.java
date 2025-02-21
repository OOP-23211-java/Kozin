package nsu.parser.io.interfaces;

import nsu.parser.io.*;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multimap;

/**
 * Интерфейс {@code IWriter} определяет контракт для классов, 
 * реализующих функциональность сортировки слов по их количеству во входном файле 
 * и записи результатов в выходной файл в формате CSV.
 * <p>
 * Основное предназначение — обеспечение единого способа сохранения статистики слов 
 * после их подсчёта. Сортировка, как правило, производится по количеству вхождений слов 
 * в тексте, что упрощает дальнейший анализ данных.
 * </p>
 *
 * <p><b>Особенности:</b></p>
 * <ul>
 *     <li>Сортировка коллекции слов по количеству вхождений.</li>
 *     <li>Запись отсортированных данных в CSV-файл.</li>
 *     <li>Гибкость для различных реализаций записи.</li>
 * </ul>
 *
 * <p><b>Пример использования:</b></p>
 * <pre>{@code
 * IWriter writer = new Writer(Path.of("output.csv"));
 * Multiset<String> wordStats = ...; // Получение статистики слов
 * writer.writeCSV(wordStats);       // Запись в CSV
 * }</pre>
 *
 * @see Multiset
 */
public interface IWriter {
    
    /**
     * Сортирует переданную коллекцию слов по количеству вхождений и записывает результат в выходной CSV-файл.
     *
     * @param wordStatMultiset {@link Multiset} — коллекция, содержащая слова и количество их вхождений.
     *                         Ключ — слово, значение — количество его появлений в тексте.
     */
    public void writeCSV(Multiset<String> wordStatMultiset);

}
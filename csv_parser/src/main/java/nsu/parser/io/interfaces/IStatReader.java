package nsu.parser.io.interfaces;

import java.io.FileNotFoundException;

import com.google.common.collect.Multiset;

/**
 * Интерфейс {@code IStatReader} определяет контракт для классов, 
 * реализующих функциональность по считыванию слов из входного файла 
 * и подсчёту их количества.
 * <p>
 * Основная цель интерфейса — предоставить метод для обработки текстовых файлов и 
 * получения структуры данных {@link Multiset}, содержащей слова и количество их вхождений.
 * </p>
 *
 * <p><b>Особенности:</b></p>
 * <ul>
 *     <li>Обработка текста с подсчётом повторений слов.</li>
 *     <li>Возврат результата в виде {@link Multiset} для удобства дальнейшей обработки.</li>
 *     <li>Гибкость при реализации различных способов чтения файлов.</li>
 * </ul>
 *
 * <p><b>Пример использования:</b></p>
 * <pre>{@code
 * IStatReader reader = new StatReader(Path.of("input.txt"));
 * Multiset<String> wordStats = reader.getWordStatMultiset();
 * System.out.println("Количество слова 'example': " + wordStats.count("example"));
 * }</pre>
 *
 * @see Multiset
 */
public interface IStatReader {
    
    /**
     * Считывает слова из входного файла, подсчитывает их количество и возвращает результат.
     *
     * @return {@link Multiset} с словами и их количеством вхождений.
     *         Ключ — слово, значение — количество его появлений в файле.
     */
    public Multiset<String> getWordStatMultiset();

}
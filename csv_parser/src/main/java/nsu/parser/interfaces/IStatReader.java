package nsu.parser.interfaces;

import com.google.common.collect.Multiset;
/**
 * Интерфейс классов, реализующих считывание и
 * подсчёт количество слов во входном файле.
 */
public interface IStatReader {
    Multiset<String> getWordStatMultiset();
}
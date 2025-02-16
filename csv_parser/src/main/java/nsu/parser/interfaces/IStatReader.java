package nsu.parser.interfaces;

import com.google.common.collect.Multiset;

public interface IStatReader {
    Multiset<String> getWordStatMultiset();
}
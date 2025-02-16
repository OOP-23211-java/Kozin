package nsu.parser.interfaces;

import nsu.parser.io.*;

import com.google.common.collect.Multiset;
import com.google.common.collect.Multimap;
/**
 *Интерфейс для классов, реализующих сортировку
 по количеству слов во входном файле, а после
 записывающих в выходной файл.
 */
public interface IWriter {
    void writeCSV();
}
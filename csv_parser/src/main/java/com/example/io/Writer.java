package com.example.io;

import com.google.common.collect.Multiset;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.io.FileWriter;

public class Writer {
    public static void writeCSV(String outputFilePath, Multiset<String> multiset) {
        Multimap<Integer, String> sortedMap = ArrayListMultimap.create();
        for (String element : multiset.elementSet()) {
            sortedMap.put(multiset.count(element), element);
        }

        try (FileWriter outputFileStream = new FileWriter(outputFilePath)) {
            for (var entry : sortedMap.entries()) {
                outputFileStream.write(entry.getValue() + "\t" + entry.getKey() + "\n");
            }
        } catch (Exception e) {
            System.out.println("Can't open output file: " + outputFilePath + "\n");
        }
    }
}

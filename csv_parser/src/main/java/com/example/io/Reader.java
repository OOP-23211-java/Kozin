package com.example.io;

import com.example.exceptions.*;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.io.File;
import java.util.Scanner;

public class Reader {
    public static Multiset<String> read(String inputFilePath) {
        File inputFileStream = new File(inputFilePath);
        if (!inputFileStream.exists()) {
            throw new InvalidInputFileException("Can't open input file: " + inputFilePath);
        }

        try (Scanner scan = new Scanner(inputFileStream)) {
            while (scan.hasNext()) {
                String word = scan.next();
                word = word.replaceAll("\\p{Punct}", "");
                word = word.toLowerCase();
                _multiset.add(word);
            }
        } catch (Exception e) {
            throw new InvalidReadFromFileException("Can't read from input file: " + inputFilePath);
        }

        return _multiset;
    }

    private static final Multiset<String> _multiset = HashMultiset.create();
}

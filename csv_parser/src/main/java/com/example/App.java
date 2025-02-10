package com.example;

import com.example.io.*;
import com.google.common.collect.Multiset;

public class App {
    public static void main(String[] args) {
        try {
            ArgumentParser.parse(args);
            String inputFilePath = args[0], outputFilePath = args[1];
            Multiset<String> multiset = Reader.read(inputFilePath);
            Writer.writeCSV(outputFilePath, multiset);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
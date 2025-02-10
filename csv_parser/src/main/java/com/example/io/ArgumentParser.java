package com.example.io;

import com.example.exceptions.*;
import java.io.File;

public class ArgumentParser {
    public static void parse(String[] args) {
        if (args.length != VALID_COUNT_ARGS) {
            throw new InvalidCountArgsException("Invalid count args: " + args.length + ", count args must be 2");
        }
        File inputFileStream = new File(args[0]);
        if (!inputFileStream.exists()) {
            throw new InvalidInputFileException("Can't open input file: " + args[0]);
        }
    }

    private final static int VALID_COUNT_ARGS = 2;
}

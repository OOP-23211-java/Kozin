package nsu.parser.io;

import java.nio.file.Files;
import java.nio.file.Path;

import nsu.parser.exceptions.*;

public class ArgumentValidator {
    private static final int VALID_COUNT_ARGS = 2;

    public static void validate(String[] args) {
        validateCountArgs(args.length);
        validateInputFilePath(args[0]);
    }

    private static void validateCountArgs(int countArgs) {
        if (countArgs != VALID_COUNT_ARGS) {
            throw new InvalidCountArgsException("Invalid count args: " + countArgs + ", count args must be 2");
        }
    }

    private static void validateInputFilePath(String inputFilePath) {
        Path path = Path.of(inputFilePath);
        if (Files.notExists(path)) {
            throw new InvalidInputFileException("Can't open input file: " + inputFilePath);
        }
    }
}



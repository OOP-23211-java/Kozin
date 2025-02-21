package nsu.parser.validators;

import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.IllegalArgumentException;
import java.io.FileNotFoundException;

import nsu.parser.exceptions.*;

/**
 * Класс {@code ArgumentValidator} выполняет валидацию аргументов командной строки для программы,
 * преобразующей текстовый файл в CSV. Он проверяет количество аргументов и существование входного файла.
 *
 * <p>После успешной валидации возвращает объект {@link ValidatedArguments}, содержащий пути к 
 * входному и выходному файлам.</p>
 *
 * <p><b>Функциональность:</b></p>
 * <ul>
 *     <li>Проверка количества аргументов командной строки.</li>
 *     <li>Проверка существования указанного входного файла.</li>
 *     <li>Возврат валидированных путей в виде объекта {@link ValidatedArguments}.</li>
 * </ul>
 *
 * <p><b>Пример использования:</b></p>
 * <pre>{@code
 * String[] args = {"input.txt", "output.csv"};
 * var validatedArgs = ArgumentValidator.validate(args);
 * Path inputPath = Path.of(validatedArgs.inputFilePath());
 * Path outputPath = Path.of(validatedArgs.outputFilePath());
 * }</pre>
 *
 * @see ValidatedArguments
 */
public class ArgumentValidator {
    private static final int VALID_COUNT_ARGS = 2;

    /**
     * Выполняет валидацию аргументов командной строки.
     * <ul>
     *     <li>Проверяет, что количество аргументов соответствует {@code VALID_COUNT_ARGS}.</li>
     *     <li>Проверяет существование входного файла по указанному пути.</li>
     * </ul>
     *
     * @param args массив аргументов командной строки:
     *             <ul>
     *                 <li>{@code args[0]} — путь к входному текстовому файлу;</li>
     *                 <li>{@code args[1]} — путь к выходному CSV файлу.</li>
     *             </ul>
     * @return объект {@link ValidatedArguments}, содержащий валидированные пути.
     * @throws IllegalArgumentException если количество аргументов неверное или входной файл не существует.
     */
    public static ValidatedArguments validate(String[] args) {
        validateCountArgs(args.length);
        validateInputFilePath(args[0]);

        return new ValidatedArguments(args[0], args[1]);
    }

    private static void validateCountArgs(int countArgs) {
        if (countArgs != VALID_COUNT_ARGS) {
            throw new IllegalArgumentException("Illegal count args " + countArgs + ", count args must be 2");
        }
    }

    private static void validateInputFilePath(String inputFilePath) {
        Path path = Path.of(inputFilePath);
        if (Files.notExists(path)) {
            throw new IllegalArgumentException("Failed open file " + inputFilePath);
        }
    }

    public record ValidatedArguments(String inputFilePath, String outputFilePath) {}

}



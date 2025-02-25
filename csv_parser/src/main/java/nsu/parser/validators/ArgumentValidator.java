package nsu.parser.validators;

import java.nio.file.Files;
import java.nio.file.Path;
import java.lang.IllegalArgumentException;
import java.io.FileNotFoundException;

import nsu.parser.exceptions.*;

/**
 * Класс {@code ArgumentValidator} выполняет валидацию аргументов командной строки для программы, 
 * преобразующей текстовый файл в CSV.  
 * <p>
 * Проверяет:
 * <ul>
 *     <li>Количество переданных аргументов.</li>
 *     <li>Существование и доступность для чтения входного файла.</li>
 *     <li>Корректность пути и права на создание/запись выходного файла.</li>
 * </ul>
 * </p>
 *
 * <p>После успешной валидации возвращает объект {@link ValidatedArguments}, содержащий валидированные 
 * пути к входному и выходному файлам.</p>
 *
 * <h3>Особенности:</h3>
 * <ul>
 *     <li>Гарантирует, что программа не будет работать с несуществующими файлами или директориями.</li>
 *     <li>Обеспечивает корректную обработку прав доступа для создания и записи в выходной файл.</li>
 *     <li>Использует {@link IllegalArgumentException} для информирования о некорректных аргументах.</li>
 * </ul>
 *
 * <h3>Пример использования:</h3>
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
     * Выполняет валидацию аргументов командной строки:
     * <ul>
     *     <li>Проверяет, что количество аргументов равно {@link #VALID_COUNT_ARGS}.</li>
     *     <li>Проверяет существование и доступность входного файла.</li>
     *     <li>Проверяет возможность создания или записи в выходной файл.</li>
     * </ul>
     *
     * @param args массив аргументов командной строки:
     *             <ul>
     *                 <li>{@code args[0]} — путь к входному текстовому файлу;</li>
     *                 <li>{@code args[1]} — путь к выходному CSV файлу.</li>
     *             </ul>
     * @return объект {@link ValidatedArguments} с валидированными путями.
     * @throws IllegalArgumentException если количество аргументов неверное, 
     *                                  входной файл не существует или отсутствуют права на запись в выходной файл.
     */
    public static ValidatedArguments validate(String[] args) {
        validateCountArgs(args.length);
        validateInputFilePath(args[0]);
        validateOutputFilePath(args[1]);

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

    private static void validateOutputFilePath(String outputFilePath) {
        Path path = Path.of(outputFilePath);
        Path parentDir = path.getParent();
        if (parentDir != null && Files.notExists(parentDir)) {
            throw new IllegalArgumentException("Failed find directory " + parentDir);
        }

        if (parentDir != null && !Files.isWritable(parentDir)) {
            throw new IllegalArgumentException("Don't have permission to create output file " + path);
        }

        if (Files.exists(path)) {
            if (!Files.isWritable(path)) {
                throw new IllegalArgumentException("Don't have permission to edit file" + path);
            }
        }
    }

    public record ValidatedArguments(String inputFilePath, String outputFilePath) {}

}



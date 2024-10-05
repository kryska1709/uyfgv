import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class TextStatistics {
    public static void main(String[] args) {
        String input = "input.txt"; // Укажите путь к вашему текстовому файлу
        String output = "output.txt"; // Укажите путь к выходному файлу

        // Читаем текст из файла
        String text = read(input);

        // Вычисляем статистику
        int totalCharacters = text.length();
        int charactersBesProbelov = text.replaceAll("\\s", "").length();
        int word = countWords(text);

        // Выводим статистику в консоль
        System.out.println("Статистика текста:");
        System.out.println("Количество символов: " + totalCharacters);
        System.out.println("Количество символов без пробелов: " + charactersBesProbelov);
        System.out.println("Количество слов: " + word);

        // Записываем статистику в файл
        writeStatisticsToFile(output, totalCharacters, charactersBesProbelov, word);

        System.out.println("Статистика сохранена в файл: " + output);

    }

    private static String read(String filePath) {
        try {
            return Files.lines(Paths.get(filePath))
                    .collect(Collectors.joining("\n"))
                    .trim();
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка при чтении файла: " + filePath, e);
        }
    }

    private static int countWords(String text) {
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(text);
        return tokenizer.countTokens();
    }

    private static void writeStatisticsToFile(String filePath, int totalCharacters, int charactersWithoutSpaces, int wordCount) {
        List<String> lines = Arrays.asList(
                "Статистика текста:",
                "Количество символов: " + totalCharacters,
                "Количество символов без пробелов: " + charactersWithoutSpaces,
                "Количество слов: " + wordCount
        );

        try {
            Files.write(Paths.get(filePath), lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка при записи в файл: " + filePath, e);
        }
    }
}

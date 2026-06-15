package m18_io_streams.practice;

/**
 * Задача 05 — Модуль 18: Копирование текстового файла
 *
 * ЗАДАНИЕ:
 *   Скопируйте содержимое файла "output.txt" в новый файл "copy.txt",
 *   читая исходный построчно (BufferedReader) и записывая (PrintWriter
 *   или BufferedWriter). После завершения выведите количество
 *   скопированных строк.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Скопировано строк: 3
 *
 * ПОДСКАЗКА:
 *   Откройте оба ресурса в одном try-with-resources через ';':
 *   try (BufferedReader r = ...; PrintWriter w = ...) { ... }
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Task05 {
    public static void main(String[] args) {
        // Ваш код здесь

        try (BufferedReader reader = new BufferedReader(new FileReader("java-course/m18_io_streams/practice/output.txt"));
             FileWriter w = new FileWriter("java-course/m18_io_streams/practice/output2.txt")){
            String line;
            int count_of_strochka = 0;
            while ((line = reader.readLine()) != null){
                count_of_strochka++;
                w.write(line + "\n");
            }
            System.out.println("Copy lines: " + count_of_strochka);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

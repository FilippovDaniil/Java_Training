package m13_lists_generics.practice;

/**
 * Задача 03 — Модуль 13: Операции со списком
 *
 * ЗАДАНИЕ:
 *   Дан список задач. Выполните операции и выводите список после каждой:
 *     1) проверьте, содержит ли список "Купить хлеб" (contains);
 *     2) найдите индекс задачи "Позвонить врачу" (indexOf);
 *     3) замените задачу с индексом 0 на "Сделать зарядку" (set);
 *     4) удалите задачу "Купить хлеб" (remove по значению);
 *     5) выведите итоговый размер списка.
 *
 * ПОДСКАЗКА:
 *   contains, indexOf, set(index, value), remove(value), size().
 */
import java.util.ArrayList;
import java.util.List;

public class Task03 {
    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>();
        tasks.add("Check pochta");
        tasks.add("Buy hleb");
        tasks.add("call doctor");
        // Ваш код здесь
        System.out.println(tasks.contains("Buy hleb"));
        System.out.println(tasks.indexOf("call doctor"));
        tasks.set(0,"Do gym");
        System.out.println(tasks);
        tasks.remove("Buy hleb");
        System.out.println(tasks);
        System.out.println(tasks.size());
    }
}

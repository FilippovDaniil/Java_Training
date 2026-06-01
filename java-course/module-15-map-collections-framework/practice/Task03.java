/**
 * Задача 03 — Модуль 15: getOrDefault, remove, containsKey
 *
 * ЗАДАНИЕ:
 *   Дан словарь "товар → количество на складе". Реализуйте операции:
 *     1) выведите остаток товара "Молоко" (если нет — 0, через getOrDefault);
 *     2) увеличьте количество "Хлеб" на 10;
 *     3) удалите товар "Сыр" (remove);
 *     4) проверьте, остался ли "Сыр" (containsKey);
 *     5) выведите весь словарь.
 *
 * ПОДСКАЗКА:
 *   getOrDefault(key, 0) безопасно вернёт 0 для отсутствующего ключа.
 */
import java.util.HashMap;
import java.util.Map;

public class Task03 {
    public static void main(String[] args) {
        Map<String, Integer> stock = new HashMap<>();
        stock.put("Хлеб", 20);
        stock.put("Сыр", 5);
        // Ваш код здесь
    }
}

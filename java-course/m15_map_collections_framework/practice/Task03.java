package m15_map_collections_framework.practice;

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
        stock.put("Hleb", 20);
        stock.put("Sir", 5);
        // Ваш код здесь
        System.out.println("Est li moloko: " + stock.getOrDefault("Milk",0));
        stock.put("Hleb",stock.get("Hleb")+10);
        stock.remove("Sir");
        System.out.println("Est li sir: " + stock.getOrDefault("Sir",0));
        System.out.println(stock);
    }
}

package m15_map_collections_framework.practice;

/**
 * Задача 07 — Модуль 15 (МИНИ-ПРОЕКТ): Складской учёт
 *
 * ЗАДАНИЕ:
 *   Реализуйте простой склад на основе Map<String, Integer>
 *   (товар → количество). Сделайте интерактивное меню:
 *     1 — приход: добавить N единиц товара (если товара нет — создать);
 *     2 — расход: списать N единиц (нельзя уйти в минус —
 *         если на складе меньше, вывести "Недостаточно товара");
 *     3 — показать остатки всех товаров (отсортированные по названию);
 *     4 — показать товары, которых осталось меньше порога (например < 5);
 *     0 — выход.
 *   В конце выведите суммарное количество всех единиц на складе.
 *
 * ПРИМЕР ОСТАТКОВ:
 *   Гвозди: 100
 *   Доски: 3
 *   Краска: 12
 *   Заканчиваются (< 5): Доски
 *
 * ПОДСКАЗКИ:
 *   - приход: map.merge(name, qty, Integer::sum) или getOrDefault;
 *   - для сортировки по названию используйте TreeMap или TreeSet ключей;
 *   - помните о «ловушке» nextInt()/nextLine() (см. модуль 03).
 */


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task07 {
    public static void main(String[] args) {
        Map<String, Integer> warehouse = new HashMap<>();
        warehouse.put("Gvozdi", 100);
        warehouse.put("Doski", 200);
        warehouse.put("Kraska", 50);
        Scanner scanner = new Scanner(System.in);
        String choice;

        while (true) {
            System.out.println("\nEnter do:");
            System.out.println("1 – do 1 - add smth");
            System.out.println("2 – do 2 - remove smth");
            System.out.println("3 – do 3 - show ostatki");
            System.out.println("4 – do 4 - show less");
            System.out.println("0 – exit");
            System.out.print("Enter number: ");

            choice = scanner.nextLine();

            if (choice.equals("0")) {
                System.out.println("Close programm");
                break;  // выходим из цикла
            }

            switch (choice) {
                case "add gvozdi":
                    warehouse.put("Gvozdi",warehouse.get("Gvozdi")+10);
                    System.out.println("Count of gvozdi: " + warehouse.get("Gvozdi"));
                    // ваша логика для 1
                    break;
                case "remove gvozdi":
                    warehouse.put("Gvozdi",warehouse.get("Gvozdi")-10);
                    System.out.println("Count of gvozdi: " + warehouse.get("Gvozdi"));
                    // логика для 2
                    break;
                case "show ostatki":
                    for (Map.Entry<String, Integer> ostatki: warehouse.entrySet()){
                        System.out.println(ostatki.getKey() + ": " + ostatki.getValue());
                    }
                    break;
                case "show less":
                    for (Map.Entry<String, Integer> ostatki: warehouse.entrySet()){
                        System.out.println(ostatki.getKey() + ": " + ostatki.getValue());
                    }
                    break;
                default:
                    System.out.println("Bad request. Please, enter 1, 2, 3, 4 or 0.");
            }
        }

        scanner.close();
    }
}

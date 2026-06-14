package m13_lists_generics.practice.task05;

/**
 * Задача 05 — Модуль 13: Список объектов
 *
 * ЗАДАНИЕ:
 *   Создайте список товаров List<Product>, добавьте несколько товаров,
 *   затем:
 *     - выведите все товары;
 *     - посчитайте суммарную стоимость;
 *     - найдите самый дорогой товар.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Молоко - 80.0
 *   Хлеб - 45.0
 *   Сыр - 560.0
 *   Сумма: 685.0
 *   Самый дорогой: Сыр
 *
 * ПОДСКАЗКА:
 *   List<Product> items = new ArrayList<>();
 *   items.add(new Product("Молоко", 80.0));
 */

import java.util.ArrayList;
import java.util.List;

public class Task05 {
    public static void main(String[] args) {
        // Создайте список Product и обработайте его
        List<Product> products = new ArrayList<>();
        products.add(new Product("Moloko",80.0));
        products.add(new Product("Hleb",45));
        products.add(new Product("Sir",560.0));

        double most_expensive = 0;
        double summa = 0;

        for (Product product : products) {
            summa = summa + product.getPrice();
            if (product.getPrice() > most_expensive){
                most_expensive = product.getPrice();
            }
            System.out.println(product.getName() + ": " + product.getPrice());
        }
        System.out.println("Summ: " + summa);

        for (Product product : products) {
            if (product.getPrice() == most_expensive){
                System.out.println("Most expensive: " + product.getName());
            }
        }

    }
}

/**
 * Задача 07 — Модуль 28 (МИНИ-ПРОЕКТ): Полный набор тестов
 *
 * ВНИМАНИЕ: JUnit-тест. Запускайте через IDE или Maven/Gradle.
 *
 * ЗАДАНИЕ:
 *   Класс ShoppingCart (корзина покупок) реализован ниже. Напишите
 *   ПОЛНОЦЕННЫЙ набор тестов, объединяющий все приёмы модуля:
 *     1) @BeforeEach — создавайте новую корзину перед каждым тестом;
 *     2) новая корзина пуста, итог 0;
 *     3) addItem увеличивает количество позиций и сумму (assertEquals);
 *     4) добавление того же товара суммирует количество, а не дублирует
 *        позицию;
 *     5) removeItem удаляет позицию;
 *     6) применение скидки уменьшает итог корректно;
 *     7) скидка вне диапазона 0..100 бросает исключение (assertThrows);
 *     8) @DisplayName на каждом тесте; где уместно — assertAll;
 *     9) параметризованный тест на applyDiscount для нескольких процентов
 *        (@CsvSource: процент → ожидаемый итог при известной сумме).
 *
 * ЦЕЛЬ:
 *   Покрыть тестами как «счастливый путь», так и граничные случаи и
 *   ошибки. Это репетиция реальной работы QA/разработчика.
 *
 * ПОДСКАЗКА:
 *   Продумайте независимость тестов (@BeforeEach), осмысленные имена
 *   (@DisplayName), проверку исключений (assertThrows) и данные
 *   (@CsvSource). Помните про дельту для double в assertEquals.
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

public class Task07 {

    ShoppingCart cart;

    @BeforeEach
    void setUp() {
        // TODO: создайте новую корзину
    }

    // TODO: напишите полный набор тестов согласно заданию

}

// Класс под тестом (готов, менять не нужно)
class ShoppingCart {
    private final java.util.Map<String, Integer> items = new java.util.HashMap<>();
    private final java.util.Map<String, Double> prices = new java.util.HashMap<>();

    void addItem(String name, double price, int qty) {
        if (qty <= 0) throw new IllegalArgumentException("Количество должно быть > 0");
        items.merge(name, qty, Integer::sum);
        prices.put(name, price);
    }

    void removeItem(String name) {
        items.remove(name);
        prices.remove(name);
    }

    int itemCount() { return items.size(); }

    boolean isEmpty() { return items.isEmpty(); }

    double total() {
        double sum = 0;
        for (var e : items.entrySet()) {
            sum += prices.get(e.getKey()) * e.getValue();
        }
        return sum;
    }

    double applyDiscount(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне 0..100");
        }
        return total() * (1 - percent / 100);
    }
}

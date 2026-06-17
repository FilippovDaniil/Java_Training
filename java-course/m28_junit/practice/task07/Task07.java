package m28_junit.practice.task07;

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
        // Создаем новую пустую корзину перед каждым тестом
        cart = new ShoppingCart();
    }

    // Тест 1: Новая корзина пуста, итог 0
    @Test
    @DisplayName("Новая корзина должна быть пустой с итогом 0")
    void testNewCartIsEmpty() {
        assertAll("new cart",
                () -> assertTrue(cart.isEmpty(), "New cart should be empty"),
                () -> assertEquals(0, cart.itemCount(), "Item count should be 0"),
                () -> assertEquals(0.0, cart.total(), 0.001, "Total should be 0.0")
        );
    }

    // Тест 2: addItem увеличивает количество позиций и сумму
    @Test
    @DisplayName("Добавление товара увеличивает количество позиций и итоговую сумму")
    void testAddItemIncreasesCountAndTotal() {
        cart.addItem("Apple", 1.5, 3);

        assertAll("after adding item",
                () -> assertFalse(cart.isEmpty(), "Cart should not be empty"),
                () -> assertEquals(1, cart.itemCount(), "Item count should be 1"),
                () -> assertEquals(4.5, cart.total(), 0.001, "Total should be 4.5")
        );

        cart.addItem("Banana", 2.0, 2);

        assertAll("after adding second item",
                () -> assertEquals(2, cart.itemCount(), "Item count should be 2"),
                () -> assertEquals(8.5, cart.total(), 0.001, "Total should be 8.5")
        );
    }

    // Тест 3: Добавление того же товара суммирует количество
    @Test
    @DisplayName("Добавление того же товара суммирует количество, а не дублирует позицию")
    void testAddSameItemSumsQuantity() {
        cart.addItem("Milk", 2.5, 2);
        assertEquals(1, cart.itemCount(), "Should have 1 item");
        assertEquals(5.0, cart.total(), 0.001, "Total should be 5.0");

        cart.addItem("Milk", 2.5, 3);
        assertEquals(1, cart.itemCount(), "Should still have 1 item (not duplicated)");
        assertEquals(12.5, cart.total(), 0.001, "Total should be 12.5 (5 units * 2.5)");

        cart.addItem("Milk", 3.0, 1); // цена может измениться
        assertEquals(1, cart.itemCount(), "Should still have 1 item");
        assertEquals(18.0, cart.total(), 0.001, "Total should be 18.0 (6 units * 3.0)");
    }

    // Тест 4: removeItem удаляет позицию
    @Test
    @DisplayName("Удаление товара удаляет позицию из корзины")
    void testRemoveItemDeletesItem() {
        cart.addItem("Bread", 1.0, 2);
        cart.addItem("Butter", 2.0, 1);

        assertEquals(2, cart.itemCount(), "Should have 2 items");
        assertEquals(4.0, cart.total(), 0.001, "Total should be 4.0");

        cart.removeItem("Bread");

        assertAll("after removing bread",
                () -> assertEquals(1, cart.itemCount(), "Should have 1 item left"),
                () -> assertEquals(2.0, cart.total(), 0.001, "Total should be 2.0")
        );

        cart.removeItem("Butter");

        assertAll("after removing butter",
                () -> assertTrue(cart.isEmpty(), "Cart should be empty"),
                () -> assertEquals(0, cart.itemCount(), "Item count should be 0"),
                () -> assertEquals(0.0, cart.total(), 0.001, "Total should be 0.0")
        );
    }

    // Тест 5: Применение скидки уменьшает итог корректно
    @Test
    @DisplayName("Применение скидки корректно уменьшает итоговую сумму")
    void testApplyDiscountReducesTotalCorrectly() {
        cart.addItem("Laptop", 1000.0, 1);
        cart.addItem("Mouse", 50.0, 2);

        assertEquals(1100.0, cart.total(), 0.001, "Total before discount should be 1100.0");

        // Проверка скидки в 10%
        double discountedTotal = cart.applyDiscount(10);
        assertEquals(990.0, discountedTotal, 0.001, "10% discount should give 990.0");

        // Проверка скидки в 50%
        discountedTotal = cart.applyDiscount(50);
        assertEquals(550.0, discountedTotal, 0.001, "50% discount should give 550.0");

        // Проверка скидки в 0% (без скидки)
        discountedTotal = cart.applyDiscount(0);
        assertEquals(1100.0, discountedTotal, 0.001, "0% discount should keep total at 1100.0");

        // Проверка скидки в 100% (бесплатно)
        discountedTotal = cart.applyDiscount(100);
        assertEquals(0.0, discountedTotal, 0.001, "100% discount should give 0.0");
    }

    // Тест 6: Скидка вне диапазона 0..100 бросает исключение
    @Test
    @DisplayName("Скидка вне диапазона 0..100 выбрасывает IllegalArgumentException")
    void testInvalidDiscountThrowsException() {
        cart.addItem("Book", 30.0, 1);

        assertAll("invalid discount values",
                () -> {
                    IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class,
                            () -> cart.applyDiscount(-1),
                            "Negative discount should throw exception");
                    assertEquals("Скидка должна быть в диапазоне 0..100", e1.getMessage());
                },
                () -> {
                    IllegalArgumentException e2 = assertThrows(IllegalArgumentException.class,
                            () -> cart.applyDiscount(101),
                            "Discount > 100 should throw exception");
                    assertEquals("Скидка должна быть в диапазоне 0..100", e2.getMessage());
                },
                () -> {
                    IllegalArgumentException e3 = assertThrows(IllegalArgumentException.class,
                            () -> cart.applyDiscount(-50),
                            "Very negative discount should throw exception");
                    assertEquals("Скидка должна быть в диапазоне 0..100", e3.getMessage());
                },
                () -> {
                    IllegalArgumentException e4 = assertThrows(IllegalArgumentException.class,
                            () -> cart.applyDiscount(150),
                            "Very large discount should throw exception");
                    assertEquals("Скидка должна быть в диапазоне 0..100", e4.getMessage());
                }
        );
    }

    // Тест 7: Параметризованный тест для applyDiscount
    @ParameterizedTest
    @CsvSource({
            "0, 100.0",
            "10, 90.0",
            "25, 75.0",
            "50, 50.0",
            "75, 25.0",
            "100, 0.0"
    })
    @DisplayName("Параметризованный тест скидок: процент -> ожидаемый итог")
    void testApplyDiscountWithDifferentPercentages(int percent, double expected) {
        // Создаем корзину с известной суммой (100.0)
        cart.addItem("Test Item", 100.0, 1);

        double result = cart.applyDiscount(percent);
        assertEquals(expected, result, 0.001,
                percent + "% discount should give " + expected);
    }

    // Тест 8: Граничные случаи с количеством
    @Test
    @DisplayName("Проверка граничных случаев при добавлении товаров")
    void testEdgeCases() {
        // Добавление товара с количеством 1
        cart.addItem("Pen", 0.5, 1);
        assertEquals(0.5, cart.total(), 0.001, "Single item cost should be 0.5");

        // Добавление товара с нулевой ценой
        cart.addItem("Free Item", 0.0, 5);
        assertEquals(0.5, cart.total(), 0.001, "Zero price items shouldn't affect total");

        // Добавление большого количества
        cart.addItem("Cheap Item", 0.01, 1000);
        assertEquals(10.5, cart.total(), 0.001, "Total should be 10.5");

        // Попытка добавить товар с неположительным количеством
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Invalid", 1.0, 0),
                "Adding item with zero quantity should throw exception");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Invalid", 1.0, -5),
                "Adding item with negative quantity should throw exception");
    }

    // Тест 9: Удаление несуществующего товара
    @Test
    @DisplayName("Удаление несуществующего товара не должно вызывать ошибок")
    void testRemoveNonExistentItem() {
        cart.addItem("Apple", 1.5, 2);
        assertEquals(1, cart.itemCount(), "Should have 1 item");

        // Удаление несуществующего товара не должно вызывать исключений
        cart.removeItem("Orange");

        assertEquals(1, cart.itemCount(), "Item count should remain 1");
        assertEquals(3.0, cart.total(), 0.001, "Total should remain 3.0");
    }

    // Тест 10: Комплексный сценарий работы с корзиной
    @Test
    @DisplayName("Комплексный сценарий: добавление, удаление и применение скидки")
    void testComplexScenario() {
        // Добавляем несколько товаров
        cart.addItem("Apple", 1.5, 4);
        cart.addItem("Banana", 2.0, 3);
        cart.addItem("Orange", 2.5, 2);

        assertEquals(3, cart.itemCount(), "Should have 3 items");
        assertEquals(17.0, cart.total(), 0.001, "Total should be 17.0");

        // Удаляем один товар
        cart.removeItem("Banana");
        assertEquals(2, cart.itemCount(), "Should have 2 items after removal");
        assertEquals(11.0, cart.total(), 0.001, "Total should be 11.0");

        // Добавляем еще товар
        cart.addItem("Grape", 3.0, 1);
        assertEquals(3, cart.itemCount(), "Should have 3 items again");
        assertEquals(14.0, cart.total(), 0.001, "Total should be 14.0");

        // Применяем скидку 20%
        double discountedTotal = cart.applyDiscount(20);
        assertEquals(11.2, discountedTotal, 0.001, "20% discount should give 11.2");

        // Проверяем, что итог корзины не изменился после применения скидки
        assertEquals(14.0, cart.total(), 0.001, "Original total should remain 14.0");
    }
}
package m34_testing_junit_mockito.practice.task06;

/**
 * Задача 06 — Модуль 34: Параметризованные тесты + моки
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: JUnit 5 (junit-jupiter-params) + Mockito.
 *
 * ЗАДАНИЕ:
 *   Класс DiscountService считает итоговую цену: берёт базовую цену из
 *   priceRepository.getPrice(productId) и применяет скидку.
 *   Напишите ПАРАМЕТРИЗОВАННЫЙ тест (@CsvSource), который для разных
 *   пар (скидка %, ожидаемая цена) проверяет расчёт. Базовую цену
 *   (например 1000) задайте через мок priceRepository.
 *
 * ПРИМЕР @CsvSource:
 *   "0, 1000", "10, 900", "50, 500", "100, 0"
 *
 * ПОДСКАЗКА:
 *   @ParameterizedTest
 *   @CsvSource({"0, 1000", "10, 900"})
 *   void test(int percent, double expected) {
 *       when(repo.getPrice(1)).thenReturn(1000.0);
 *       assertEquals(expected, service.finalPrice(1, percent), 0.001);
 *   }
 */

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Task06 {

    // TODO: параметризованный тест с моком priceRepository

}

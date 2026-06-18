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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Task06 {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private DiscountService discountService;

    private final long PRODUCT_ID = 1L;
    private final double BASE_PRICE = 1000.0;

    @ParameterizedTest
    @CsvSource({
            "0, 1000.0",
            "10, 900.0",
            "20, 800.0",
            "25, 750.0",
            "30, 700.0",
            "50, 500.0",
            "75, 250.0",
            "100, 0.0"
    })
    void testFinalPriceWithDiscount(int discountPercent, double expectedPrice) {
        // Arrange - настраиваем мок
        when(priceRepository.getPrice(PRODUCT_ID)).thenReturn(BASE_PRICE);

        // Act - выполняем метод
        double actualPrice = discountService.finalPrice(PRODUCT_ID, discountPercent);

        // Assert - проверяем результат
        assertEquals(expectedPrice, actualPrice, 0.001,
                "Цена со скидкой " + discountPercent + "% должна быть " + expectedPrice);

        // Проверяем, что метод был вызван ровно 1 раз
        verify(priceRepository, times(1)).getPrice(PRODUCT_ID);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 100.0",
            "10, 90.0",
            "20, 80.0",
            "50, 50.0",
            "100, 0.0"
    })
    void testFinalPriceWithDifferentBasePrice(int discountPercent, double expectedPrice) {
        // Arrange - другая базовая цена
        double basePrice = 100.0;
        when(priceRepository.getPrice(PRODUCT_ID)).thenReturn(basePrice);

        // Act
        double actualPrice = discountService.finalPrice(PRODUCT_ID, discountPercent);

        // Assert
        assertEquals(expectedPrice, actualPrice, 0.001);
        verify(priceRepository, times(1)).getPrice(PRODUCT_ID);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 5000.0",
            "10, 4500.0",
            "25, 3750.0",
            "33, 3350.0",
            "50, 2500.0",
            "100, 0.0"
    })
    void testFinalPriceWithLargeBasePrice(int discountPercent, double expectedPrice) {
        // Arrange - большая базовая цена
        double basePrice = 5000.0;
        when(priceRepository.getPrice(PRODUCT_ID)).thenReturn(basePrice);

        // Act
        double actualPrice = discountService.finalPrice(PRODUCT_ID, discountPercent);

        // Assert
        assertEquals(expectedPrice, actualPrice, 0.001);
        verify(priceRepository, times(1)).getPrice(PRODUCT_ID);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0.99",
            "10, 0.891",
            "20, 0.792",
            "50, 0.495",
            "100, 0.0"
    })
    void testFinalPriceWithDecimalBasePrice(int discountPercent, double expectedPrice) {
        // Arrange - базовая цена с дробной частью
        double basePrice = 0.99;
        when(priceRepository.getPrice(PRODUCT_ID)).thenReturn(basePrice);

        // Act
        double actualPrice = discountService.finalPrice(PRODUCT_ID, discountPercent);

        // Assert
        assertEquals(expectedPrice, actualPrice, 0.001);
        verify(priceRepository, times(1)).getPrice(PRODUCT_ID);
    }

    @Test
    void testFinalPriceWithDifferentProductIds() {
        // Arrange
        long productId1 = 1L;
        long productId2 = 2L;
        when(priceRepository.getPrice(productId1)).thenReturn(1000.0);
        when(priceRepository.getPrice(productId2)).thenReturn(2000.0);

        // Act & Assert
        assertEquals(900.0, discountService.finalPrice(productId1, 10), 0.001);
        assertEquals(1800.0, discountService.finalPrice(productId2, 10), 0.001);

        verify(priceRepository, times(1)).getPrice(productId1);
        verify(priceRepository, times(1)).getPrice(productId2);
    }

    @Test
    void testFinalPriceWithZeroProductId() {
        // Arrange
        long productId = 0L;
        double basePrice = 500.0;
        when(priceRepository.getPrice(productId)).thenReturn(basePrice);

        // Act
        double actualPrice = discountService.finalPrice(productId, 20);

        // Assert
        assertEquals(400.0, actualPrice, 0.001);
        verify(priceRepository, times(1)).getPrice(productId);
    }
}

package m34_testing_junit_mockito.practice.task07;

/**
 * Задача 07 — Модуль 34 (МИНИ-ПРОЕКТ): Полное тестирование сервиса заказов
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: JUnit 5 + Mockito.
 *
 * ЗАДАНИЕ:
 *   Класс OrderService (ниже) зависит от трёх компонентов: склад
 *   (Inventory), оплата (PaymentGateway), уведомления (Notifier).
 *   Напишите ПОЛНЫЙ набор тестов с моками, покрывающий сценарии:
 *     1) успешный заказ: товар в наличии + оплата прошла →
 *        вернуть true, списать товар (verify inventory.reduce),
 *        отправить уведомление (verify notifier.notify);
 *     2) товара нет в наличии → вернуть false, оплата НЕ вызывается
 *        (verify gateway never), уведомление НЕ отправляется;
 *     3) товар есть, но оплата отклонена → вернуть false, товар НЕ
 *        списывается;
 *     4) проверьте через ArgumentCaptor текст уведомления при успехе;
 *     5) используйте @ExtendWith(MockitoExtension.class) + @Mock + @InjectMocks.
 *
 * ЦЕЛЬ:
 *   Прочувствовать изолированное модульное тестирование: вся логика
 *   ветвления OrderService проверяется без реальных склада/оплаты/почты.
 *
 * ПОДСКАЗКА:
 *   Комбинируйте when/thenReturn (задать сценарий), verify/never
 *   (проверить взаимодействия) и ArgumentCaptor (проверить аргументы).
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Task07 {

    @Mock
    private Inventory inventory;

    @Mock
    private PaymentGateway gateway;

    @Mock
    private Notifier notifier;

    @InjectMocks
    private OrderService orderService;

    private final String PRODUCT = "Laptop";
    private final double PRICE = 1000.0;

    // ==================== СЧАСТЛИВЫЙ ПУТЬ ====================
    @Test
    void testSuccessfulOrder() {
        // Arrange - все успешно
        when(inventory.inStock(PRODUCT)).thenReturn(true);
        when(gateway.charge(PRICE)).thenReturn(true);

        // Act
        boolean result = orderService.order(PRODUCT, PRICE);

        // Assert
        assertTrue(result, "Заказ должен быть успешным");

        // Проверяем все взаимодействия
        verify(inventory, times(1)).inStock(PRODUCT);
        verify(gateway, times(1)).charge(PRICE);
        verify(inventory, times(1)).reduce(PRODUCT);
        verify(notifier, times(1)).notify("Заказ оформлен: " + PRODUCT);

        // Проверяем порядок вызовов
        inOrder(inventory, gateway, notifier).verify(inventory).inStock(PRODUCT);
        inOrder(inventory, gateway, notifier).verify(gateway).charge(PRICE);
        inOrder(inventory, gateway, notifier).verify(inventory).reduce(PRODUCT);
        inOrder(inventory, gateway, notifier).verify(notifier).notify("Заказ оформлен: " + PRODUCT);

        verifyNoMoreInteractions(inventory, gateway, notifier);
    }

    // ==================== НЕТОВАР ====================
    @Test
    void testOrderFailsWhenProductNotInStock() {
        // Arrange - товара нет на складе
        when(inventory.inStock(PRODUCT)).thenReturn(false);

        // Act
        boolean result = orderService.order(PRODUCT, PRICE);

        // Assert
        assertFalse(result, "Заказ должен провалиться, если товара нет");

        // Проверяем, что только inStock был вызван
        verify(inventory, times(1)).inStock(PRODUCT);
        verify(gateway, never()).charge(anyDouble());
        verify(inventory, never()).reduce(anyString());
        verify(notifier, never()).notify(anyString());

        verifyNoMoreInteractions(inventory, gateway, notifier);
    }

    // ==================== ОШИБКА ПЛАТЕЖА ====================
    @Test
    void testOrderFailsWhenPaymentFails() {
        // Arrange - товар есть, но платеж не проходит
        when(inventory.inStock(PRODUCT)).thenReturn(true);
        when(gateway.charge(PRICE)).thenReturn(false);

        // Act
        boolean result = orderService.order(PRODUCT, PRICE);

        // Assert
        assertFalse(result, "Заказ должен провалиться, если платеж не прошел");

        // Проверяем вызовы
        verify(inventory, times(1)).inStock(PRODUCT);
        verify(gateway, times(1)).charge(PRICE);
        verify(inventory, never()).reduce(anyString());
        verify(notifier, never()).notify(anyString());

        verifyNoMoreInteractions(inventory, gateway, notifier);
    }

    // ==================== ПРОВЕРКА ПАРАМЕТРОВ ====================
    @Test
    void testOrderWithDifferentProducts() {
        // Arrange
        String product1 = "Phone";
        String product2 = "Tablet";
        double price1 = 500.0;
        double price2 = 300.0;

        when(inventory.inStock(product1)).thenReturn(true);
        when(inventory.inStock(product2)).thenReturn(true);
        when(gateway.charge(price1)).thenReturn(true);
        when(gateway.charge(price2)).thenReturn(true);

        // Act
        boolean result1 = orderService.order(product1, price1);
        boolean result2 = orderService.order(product2, price2);

        // Assert
        assertTrue(result1);
        assertTrue(result2);

        verify(inventory, times(1)).inStock(product1);
        verify(inventory, times(1)).inStock(product2);
        verify(gateway, times(1)).charge(price1);
        verify(gateway, times(1)).charge(price2);
        verify(inventory, times(1)).reduce(product1);
        verify(inventory, times(1)).reduce(product2);
        verify(notifier, times(1)).notify("Заказ оформлен: " + product1);
        verify(notifier, times(1)).notify("Заказ оформлен: " + product2);
    }

    // ==================== ПРОВЕРКА СООБЩЕНИЯ ====================
    @Test
    void testNotifierReceivesCorrectMessage() {
        // Arrange
        when(inventory.inStock(PRODUCT)).thenReturn(true);
        when(gateway.charge(PRICE)).thenReturn(true);

        // Act
        orderService.order(PRODUCT, PRICE);

        // Assert - перехватываем сообщение
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(notifier, times(1)).notify(messageCaptor.capture());

        String actualMessage = messageCaptor.getValue();
        assertEquals("Заказ оформлен: " + PRODUCT, actualMessage,
                "Сообщение должно содержать правильный текст");
        assertTrue(actualMessage.contains(PRODUCT),
                "Сообщение должно содержать название товара");
    }

    // ==================== ПРОВЕРКА ПОРЯДКА ====================
    @Test
    void testOrderOperationsInCorrectOrder() {
        // Arrange
        when(inventory.inStock(PRODUCT)).thenReturn(true);
        when(gateway.charge(PRICE)).thenReturn(true);

        // Act
        orderService.order(PRODUCT, PRICE);

        // Assert - проверяем строгий порядок вызовов
        InOrder inOrder = inOrder(inventory, gateway, notifier);

        inOrder.verify(inventory).inStock(PRODUCT);          // 1. Проверка наличия
        inOrder.verify(gateway).charge(PRICE);              // 2. Платеж
        inOrder.verify(inventory).reduce(PRODUCT);           // 3. Уменьшение остатка
        inOrder.verify(notifier).notify("Заказ оформлен: " + PRODUCT); // 4. Уведомление

        // Проверяем, что после этого больше не было вызовов
        verifyNoMoreInteractions(inventory, gateway, notifier);
    }

    // ==================== ПРОВЕРКА РАЗЛИЧНЫХ СЦЕНАРИЕВ ====================
    @Test
    void testOrderWithMultipleScenarios() {
        // Сценарий 1: Успешный заказ
        when(inventory.inStock("Book")).thenReturn(true);
        when(gateway.charge(20.0)).thenReturn(true);
        assertTrue(orderService.order("Book", 20.0));
        verify(inventory, times(1)).reduce("Book");

        // Сценарий 2: Нет на складе
        when(inventory.inStock("OutOfStock")).thenReturn(false);
        assertFalse(orderService.order("OutOfStock", 100.0));
        verify(gateway, never()).charge(100.0);

        // Сценарий 3: Ошибка платежа
        when(inventory.inStock("Expensive")).thenReturn(true);
        when(gateway.charge(10000.0)).thenReturn(false);
        assertFalse(orderService.order("Expensive", 10000.0));
        verify(inventory, never()).reduce("Expensive");

        // Проверяем общее количество вызовов
        verify(inventory, times(2)).inStock(anyString());
        verify(gateway, times(2)).charge(anyDouble());
        verify(notifier, times(1)).notify(anyString());
    }

    // ==================== ПРОВЕРКА НЕ ВЫЗВАННЫХ МЕТОДОВ ====================
    @Test
    void testReduceNotCalledWhenPaymentFails() {
        // Arrange
        when(inventory.inStock(PRODUCT)).thenReturn(true);
        when(gateway.charge(PRICE)).thenReturn(false);

        // Act
        orderService.order(PRODUCT, PRICE);

        // Assert
        verify(inventory, times(1)).inStock(PRODUCT);
        verify(gateway, times(1)).charge(PRICE);
        verify(inventory, never()).reduce(anyString());
        verify(notifier, never()).notify(anyString());
        verifyNoInteractions(notifier);
    }

    // ==================== ПРОВЕРКА С НЕСКОЛЬКИМИ ТОВАРАМИ ====================
    @Test
    void testOrderWithMultipleProductsAndPrices() {
        // Arrange
        Object[][] testData = {
                {"Laptop", 1000.0},
                {"Phone", 500.0},
                {"Mouse", 50.0},
                {"Keyboard", 80.0}
        };

        for (Object[] data : testData) {
            String product = (String) data[0];
            double price = (double) data[1];
            when(inventory.inStock(product)).thenReturn(true);
            when(gateway.charge(price)).thenReturn(true);
        }

        // Act
        for (Object[] data : testData) {
            String product = (String) data[0];
            double price = (double) data[1];
            assertTrue(orderService.order(product, price));
        }

        // Assert
        for (Object[] data : testData) {
            String product = (String) data[0];
            double price = (double) data[1];
            verify(inventory, times(1)).inStock(product);
            verify(gateway, times(1)).charge(price);
            verify(inventory, times(1)).reduce(product);
            verify(notifier, times(1)).notify("Заказ оформлен: " + product);
        }
    }
}

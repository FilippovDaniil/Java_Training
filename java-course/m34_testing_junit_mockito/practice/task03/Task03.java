package m34_testing_junit_mockito.practice.task03;

/**
 * Задача 03 — Модуль 34: @Mock и @InjectMocks
 *
 * ТРЕБУЮТСЯ ЗАВИСИМОСТИ: JUnit 5 + Mockito.
 *
 * ЗАДАНИЕ:
 *   Перепишите тест OrderService, используя аннотации:
 *     - @ExtendWith(MockitoExtension.class) на класс;
 *     - @Mock для PaymentGateway;
 *     - @InjectMocks для OrderService (Mockito сам подставит мок).
 *   Напишите тесты:
 *     1) если gateway.charge(amount) вернул true — placeOrder вернёт true;
 *     2) если false — placeOrder вернёт false.
 *
 * ПОДСКАЗКА:
 *   @ExtendWith(MockitoExtension.class)
 *   public class Task03 { @Mock PaymentGateway g; @InjectMocks OrderService s; ... }
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Task03 {

    @Mock
    PaymentGateway gateway;
    @InjectMocks
    OrderService service;

    @Test
    void testSuccessfulOrder() {
        // when(gateway.charge(...)).thenReturn(true); assertTrue(service.placeOrder(...))
        when(gateway.charge(100)).thenReturn(true);
        assertTrue(service.placeOrder(100));
        when(gateway.charge(200)).thenReturn(false);
        assertFalse(service.placeOrder(200));
    }

    // TODO: тест на неуспешную оплату

}

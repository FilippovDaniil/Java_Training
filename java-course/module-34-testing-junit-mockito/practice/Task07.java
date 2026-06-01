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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Task07 {

    @Mock Inventory inventory;
    @Mock PaymentGateway gateway;
    @Mock Notifier notifier;

    @InjectMocks OrderService service;

    // TODO: напишите тесты для всех сценариев из задания

}

// Классы под тестом (готовы)
interface Inventory {
    boolean inStock(String product);
    void reduce(String product);
}
interface PaymentGateway { boolean charge(double amount); }
interface Notifier { void notify(String message); }

class OrderService {
    private final Inventory inventory;
    private final PaymentGateway gateway;
    private final Notifier notifier;

    OrderService(Inventory inventory, PaymentGateway gateway, Notifier notifier) {
        this.inventory = inventory;
        this.gateway = gateway;
        this.notifier = notifier;
    }

    boolean order(String product, double price) {
        if (!inventory.inStock(product)) {
            return false;
        }
        if (!gateway.charge(price)) {
            return false;
        }
        inventory.reduce(product);
        notifier.notify("Заказ оформлен: " + product);
        return true;
    }
}

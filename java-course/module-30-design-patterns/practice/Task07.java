/**
 * Задача 07 — Модуль 30 (МИНИ-ПРОЕКТ): Кофейня (комбинация паттернов)
 *
 * ЗАДАНИЕ:
 *   Постройте мини-систему заказа кофе, объединив НЕСКОЛЬКО паттернов:
 *
 *   1. FACTORY METHOD — DrinkFactory создаёт напиток по названию
 *      ("espresso", "latte", "americano") → интерфейс Drink (cost(), name()).
 *   2. DECORATOR — добавки оборачивают напиток, увеличивая цену и
 *      описание: MilkDecorator, SyrupDecorator (см. модуль 19).
 *   3. STRATEGY — стратегия скидки DiscountStrategy (NoDiscount,
 *      PercentDiscount) применяется к итоговой цене.
 *   4. OBSERVER — OrderBoard уведомляет «бариста» (наблюдателей) о
 *      новом заказе.
 *
 *   В main: соберите заказ (напиток из фабрики + декораторы),
 *   примените стратегию скидки, оформите заказ через OrderBoard,
 *   который уведомит подписчиков и выведет итог.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Бариста уведомлён: Латте + молоко + сироп
 *   Касса уведомлена: Латте + молоко + сироп
 *   Итог со скидкой 10%: 180.0
 *
 * ПОДСКАЗКА:
 *   Это демонстрация того, как паттерны СОЧЕТАЮТСЯ в реальной системе.
 *   Делайте по шагам: сперва Factory+Drink, затем декораторы, затем
 *   стратегия скидки, затем Observer для уведомлений.
 */
import java.util.ArrayList;
import java.util.List;

public class Task07 {
    public static void main(String[] args) {
        // Соберите заказ: фабрика -> декораторы -> стратегия скидки -> observer
    }
}

interface Drink {
    String name();
    double cost();
}

interface DiscountStrategy {
    double apply(double price);
}

interface OrderObserver {
    void onNewOrder(String description);
}

// TODO: реализуйте DrinkFactory, напитки, декораторы, стратегии скидок,
//       OrderBoard (субъект Observer) и наблюдателей

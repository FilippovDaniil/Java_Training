package m30_design_patterns.practice.task07;

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
        System.out.println("=== МИНИ-ПРОЕКТ: Кофейня ===\n");

        // 1. Создаем доску заказов (Observer)
        System.out.println("--- Создание системы уведомлений ---");
        OrderBoard orderBoard = new OrderBoard();

        // Создаем наблюдателей
        BaristaObserver barista1 = new BaristaObserver("Анна");
        BaristaObserver barista2 = new BaristaObserver("Михаил");
        CashierObserver cashier = new CashierObserver("Елена");
        ManagerObserver manager = new ManagerObserver("Дмитрий");

        // Подписываем наблюдателей
        orderBoard.subscribe(barista1);
        orderBoard.subscribe(barista2);
        orderBoard.subscribe(cashier);
        orderBoard.subscribe(manager);

        System.out.println("\n" + "=" .repeat(60));

        // 2. Создаем заказы через Factory + Decorators + Strategy
        System.out.println("\n--- Заказ №1: Классический ---");
        createAndPlaceOrder(orderBoard, "espresso", "Без скидки", 0);

        System.out.println("\n" + "=" .repeat(60));
        System.out.println("\n--- Заказ №2: Латте с добавками ---");
        createAndPlaceOrder(orderBoard, "latte", "Скидка 10%", 10);

        System.out.println("\n" .repeat(60));
        System.out.println("\n--- Заказ №3: Капучино с сиропом ---");
        createAndPlaceOrder(orderBoard, "cappuccino", "Счастливый час", 0);

        System.out.println("\n" .repeat(60));
        System.out.println("\n--- Заказ №4: Американо со сливками ---");
        createAndPlaceOrder(orderBoard, "americano", "Фиксированная скидка", 30);

        System.out.println("\n" .repeat(60));
        System.out.println("\n--- Заказ №5: Сложный заказ ---");
        createComplexOrder(orderBoard);

        // 3. Показываем историю заказов
        System.out.println("\n" + "=" .repeat(60));
        orderBoard.printHistory();

        // 4. Статистика
        System.out.println("\n📊 Статистика:");
        System.out.println("   Всего заказов: " + orderBoard.getOrderCount());
        System.out.println("   Наблюдателей: " + 4);

        System.out.println("\n✅ Программа завершена!");
    }

    // Метод для создания и размещения заказа
    private static void createAndPlaceOrder(OrderBoard orderBoard, String drinkType,
                                            String discountType, double discountValue) {
        // 1. Создаем напиток через Factory
        System.out.println("   📝 Создание напитка: " + drinkType);
        Drink drink = DrinkFactory.createDrink(drinkType);

        // 2. Применяем декораторы (добавки)
        drink = addDecorators(drink);

        // 3. Вычисляем цену
        double price = drink.cost();

        // 4. Применяем стратегию скидки
        DiscountStrategy strategy = getDiscountStrategy(discountType, discountValue);
        double discountedPrice = strategy.apply(price);

        // 5. Выводим детали заказа
        System.out.println("\n   📋 Детали заказа:");
        System.out.println("   Напиток: " + drink.name());
        System.out.println("   Цена без скидки: " + String.format("%.2f", price) + " руб");
        System.out.println("   Стратегия скидки: " + strategy);
        System.out.println("   Цена со скидкой: " + String.format("%.2f", discountedPrice) + " руб");

        // 6. Размещаем заказ через OrderBoard
        orderBoard.placeOrder(drink.name(), discountedPrice);
    }

    // Метод для создания сложного заказа
    private static void createComplexOrder(OrderBoard orderBoard) {
        System.out.println("   📝 Создание сложного заказа: Латте с множеством добавок");

        // Создаем базовый напиток
        Drink drink = DrinkFactory.createDrink("latte");

        // Добавляем несколько декораторов
        drink = new MilkDecorator(drink);
        drink = new SyrupDecorator(drink, "ваниль");
        drink = new ChocolateDecorator(drink);
        drink = new CreamDecorator(drink);

        // Цена и скидка
        double price = drink.cost();
        DiscountStrategy strategy = new PercentDiscount(15);
        double discountedPrice = strategy.apply(price);

        System.out.println("\n   📋 Детали заказа:");
        System.out.println("   Напиток: " + drink.name());
        System.out.println("   Цена без скидки: " + String.format("%.2f", price) + " руб");
        System.out.println("   Стратегия скидки: " + strategy);
        System.out.println("   Цена со скидкой: " + String.format("%.2f", discountedPrice) + " руб");

        orderBoard.placeOrder(drink.name(), discountedPrice);
    }

    // Вспомогательный метод для добавления декораторов
    private static Drink addDecorators(Drink drink) {
        // В реальном приложении здесь была бы логика выбора добавок
        // Для демонстрации добавляем случайные декораторы
        return drink; // В этой версии добавки добавляются в конкретных заказах
    }

    // Вспомогательный метод для получения стратегии скидки
    private static DiscountStrategy getDiscountStrategy(String type, double value) {
        switch (type) {
            case "Без скидки":
                return new NoDiscount();
            case "Скидка 10%":
                return new PercentDiscount(10);
            case "Счастливый час":
                return new HappyHourDiscount();
            case "Фиксированная скидка":
                return new FixedDiscount(value);
            default:
                return new NoDiscount();
        }
    }
}

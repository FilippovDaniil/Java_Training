package m30_design_patterns.practice.task04;

import java.util.ArrayList;

/**
 * Задача 04 — Модуль 30: Strategy (стратегия)
 *
 * ЗАДАНИЕ:
 *   Реализуйте выбор алгоритма оплаты во время выполнения.
 *   1. Интерфейс PaymentStrategy с методом pay(double amount).
 *   2. Реализации: CardPayment, CashPayment, CryptoPayment
 *      (каждая печатает свой способ).
 *   3. Класс ShoppingCart хранит выбранную стратегию и метод
 *      checkout(double amount), делегирующий оплату стратегии;
 *      метод setStrategy(...) позволяет менять стратегию на лету.
 *   4. В main оплатите один и тот же заказ разными стратегиями.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Оплата картой: 1000.0
 *   Оплата наличными: 1000.0
 *   Оплата криптовалютой: 1000.0
 *
 * ПОДСКАЗКА:
 *   cart.setStrategy(new CardPayment()); cart.checkout(1000);
 *   Стратегию можно задать и лямбдой: amount -> System.out.println(...).
 */

public class Task04 {
    public static void main(String[] args) {
        // Меняйте стратегию оплаты и вызывайте checkout
        ArrayList<ShoppingCart> arrayList = new ArrayList<>();
        arrayList.add(new ShoppingCart(new CardPayment()));
        arrayList.add(new ShoppingCart(new CashPayment()));
        arrayList.add(new ShoppingCart(new CryptoPayment()));
        for (ShoppingCart shoppingCart : arrayList) {
            shoppingCart.checkOut(1000);
        }
    }
}

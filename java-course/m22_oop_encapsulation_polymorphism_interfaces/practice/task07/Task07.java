package m22_oop_encapsulation_polymorphism_interfaces.practice.task07;

/**
 * Задача 07 — Модуль 22 (МИНИ-ПРОЕКТ): Система оплаты
 *
 * ЗАДАНИЕ:
 *   Спроектируйте полиморфную систему оплаты через интерфейс.
 *   1. Интерфейс PaymentMethod с методами:
 *        - boolean pay(double amount)  — провести платёж (true/false);
 *        - String name()               — название способа оплаты.
 *   2. Реализации:
 *        - CreditCard (поле balance): платит, если хватает средств,
 *          списывает сумму, иначе возвращает false;
 *        - Cash (поле cash): аналогично, наличными;
 *        - DigitalWallet (поле balance + бонусы): можно частично
 *          оплачивать бонусами — придумайте логику.
 *   3. Класс Order с методом checkout(PaymentMethod method, double amount):
 *      принимает ЛЮБОЙ способ оплаты (полиморфизм) и печатает результат.
 *   4. В main создайте массив PaymentMethod[] и проведите оплату
 *      заказа разными способами, в т.ч. с нехваткой средств.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   Оплата картой Visa: 500.0 — успешно (остаток 500.0)
 *   Оплата наличными: 1500.0 — отказ (недостаточно)
 *   Оплата кошельком: 300.0 — успешно
 *
 * ПОДСКАЗКА:
 *   checkout работает с типом PaymentMethod, не зная конкретный класс —
 *   это и есть сила полиморфизма. Каждая реализация решает сама,
 *   как именно проводить платёж.
 */

// TODO: CreditCard, Cash, DigitalWallet (implements PaymentMethod) и класс Order
public class Task07 {
    public static void main(String[] args) {
        // Создайте способы оплаты, проведите заказ разными методами
        Order order = new Order(500);
        PaymentMethod paymentMethod = new CreditCard(1001);
        System.out.println(order.checkOut(paymentMethod));
        System.out.println(order.checkOut(paymentMethod));
        System.out.println(order.checkOut(paymentMethod));

        System.out.println("----------------------------------------------");

        Order order1 = new Order(1000);
        PaymentMethod paymentMethod1 = new Cash(1001);
        System.out.println(order1.checkOut(paymentMethod1));
        System.out.println(order1.checkOut(paymentMethod1));
        System.out.println(order1.checkOut(paymentMethod1));

        System.out.println("-----------------------------------------------");

        Order order2 = new Order(2000);
        PaymentMethod paymentMethod2 = new DIgitalWallet(10001,15);
        System.out.println(order2.checkOut(paymentMethod2));
        System.out.println(order2.checkOut(paymentMethod2));
        System.out.println(order2.checkOut(paymentMethod2));
    }
}

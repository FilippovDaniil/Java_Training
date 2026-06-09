/**
 * Задача 07 — Тема 08 (МИНИ-ПРОЕКТ OPS): оформление с фасадом и кешем
 *
 * Развитие OPS. Объедините два структурных паттерна:
 *   - FACADE — один вход checkout(sku), скрывающий каталог + склад + оплату;
 *   - PROXY (кеширующий) — поверх «дорогого» сервиса цен, чтобы повторные
 *     обращения к тому же sku не били по БД.
 *
 * ЗАДАНИЕ:
 *   1. CatalogService (файл CatalogService.java): long priceOf(String sku);
 *      RealCatalogService печатает "запрос цены: <sku>" и возвращает цену
 *      (sku.length() * 1000); CachingCatalogProxy кеширует цены по sku.
 *   2. Подсистемы: InventoryService (файл InventoryService.java)
 *      boolean reserve(String sku); PaymentService (файл PaymentService.java)
 *      boolean charge(long amountCents).
 *   3. CheckoutFacade (файл CheckoutFacade.java): принимает CatalogService,
 *      InventoryService, PaymentService; метод String checkout(String sku):
 *      узнать цену (через каталог/прокси), reserve, charge, вернуть
 *      "оформлено <sku> за <price>" (или текст отказа).
 *   В main: соберите фасад с CachingCatalogProxy(RealCatalogService) и оформите
 *   ОДИН и тот же sku дважды — второй раз цена берётся из кеша (без "запрос цены").
 *
 * ОЖИДАЕМЫЙ ВЫВОД (пример):
 *   запрос цены: BOOK
 *   оформлено BOOK за 4000
 *   оформлено BOOK за 4000   (цена из кеша, без запроса)
 *
 * ТРЕБОВАНИЯ:
 *   - клиент вызывает только CheckoutFacade.checkout(...);
 *   - повторное оформление того же sku не обращается к RealCatalogService (кеш);
 *   - фасад зависит от интерфейса CatalogService — ему всё равно, прокси это или нет (DIP).
 *
 * ПОДСКАЗКА:
 *   Прокси и реальный сервис взаимозаменяемы для фасада. Это сочетание Facade
 *   (упрощение) и Proxy (кеш/контроль) — типичный приём в прикладных системах.
 */

public class Task07 {
    public static void main(String[] args) {
        // TODO: соберите CheckoutFacade с CachingCatalogProxy(RealCatalogService),
        //       оформите один sku дважды
    }
}

package m01_oop_principles.practice.task07;

abstract class Product {
    // TODO: приватные поля id (String), title (String), priceCents (long)

    // TODO: конструктор Product(String id, String title, long priceCents)
    //       с валидацией (id/title не пустые, priceCents >= 0)

    // TODO: геттеры getId(), getTitle(), getPriceCents()

    // стоимость доставки этого товара — у каждого подтипа своя
    public abstract long shippingCostCents();
}

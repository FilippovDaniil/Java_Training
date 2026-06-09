// ЗАКРЫТ для модификации: не знает о конкретных политиках, работает только
// с абстракцией DiscountPolicy. Новые скидки добавляются снаружи.
class PriceCalculator {

    // TODO: поле DiscountPolicy policy + конструктор PriceCalculator(DiscountPolicy policy)

    public long finalPrice(long basePriceCents) {
        // TODO: применить policy к базовой цене
        return basePriceCents;
    }
}

// Это НОВЫЙ способ доставки. Обратите внимание: чтобы его добавить, не пришлось
// править StandardShipping/ExpressShipping — система открыта для расширения (OCP).
class DroneShipping implements ShippingMethod {
    @Override
    public String name() {
        // TODO
        return "";
    }

    @Override
    public long costCents(int weightGrams) {
        // TODO: фиксированная стоимость 500 коп. независимо от веса
        return 0;
    }
}

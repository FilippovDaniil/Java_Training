package m02_solid_srp_ocp.practice.task07;

/** Позиция заказа — данные (готово, менять не нужно). */
class OrderLine {
    private final long priceCents;
    private final int weightGrams;

    OrderLine(long priceCents, int weightGrams) {
        this.priceCents = priceCents;
        this.weightGrams = weightGrams;
    }

    long getPriceCents() { return priceCents; }
    int getWeightGrams() { return weightGrams; }
}

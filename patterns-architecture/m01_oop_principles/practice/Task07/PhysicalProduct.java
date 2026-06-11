package m01_oop_principles.practice.task07;

class PhysicalProduct extends Product {

    // TODO: поле weightGrams (int) + конструктор
    //       PhysicalProduct(String id, String title, long priceCents, int weightGrams)

    @Override
    public long shippingCostCents() {
        // TODO: 50 коп. за каждые начатые 100 г: ((weightGrams + 99) / 100) * 50
        return 0;
    }
}

package m23_microservices_vs_monolith.practice.task03;

// Локальная (in-process) реализация контракта.
class LocalPricingService implements PricingService {
    @Override
    public long priceOf(String sku) {
        // TODO: sku.length() * 1000L
        return 0;
    }
}

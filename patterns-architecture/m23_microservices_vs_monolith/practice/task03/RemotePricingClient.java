package m23_microservices_vs_monolith.practice.task03;

// «Удалённая» реализация контракта (имитация сетевого вызова).
class RemotePricingClient implements PricingService {
    @Override
    public long priceOf(String sku) {
        // TODO: напечатать "[remote] вызов priceOf(" + sku + ")"; вернуть sku.length() * 1000L
        return 0;
    }
}

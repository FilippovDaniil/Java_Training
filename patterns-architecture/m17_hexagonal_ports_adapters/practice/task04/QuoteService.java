package m17_hexagonal_ports_adapters.practice.task04;

// Ядро: зависит от выходного порта PriceProvider, не от конкретного адаптера.
class QuoteService {
    // TODO: поле final PriceProvider provider + конструктор QuoteService(PriceProvider provider)

    public long quote(String sku) {
        // TODO: provider.priceOf(sku)
        return 0;
    }
}

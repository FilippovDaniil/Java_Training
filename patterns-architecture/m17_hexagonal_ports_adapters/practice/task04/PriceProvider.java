package m17_hexagonal_ports_adapters.practice.task04;

// Выходной (driven) порт.
interface PriceProvider {
    long priceOf(String sku);
}

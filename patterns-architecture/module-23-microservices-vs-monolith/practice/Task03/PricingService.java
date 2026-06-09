// Контракт сервиса (граница). За ним — локальная или удалённая реализация.
interface PricingService {
    long priceOf(String sku);
}

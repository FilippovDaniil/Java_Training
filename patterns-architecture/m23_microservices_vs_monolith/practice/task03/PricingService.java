package m23_microservices_vs_monolith.practice.task03;

// Контракт сервиса (граница). За ним — локальная или удалённая реализация.
interface PricingService {
    long priceOf(String sku);
}

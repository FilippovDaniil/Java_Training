package m17_hexagonal_ports_adapters.practice.task02;

// Входной (driving) порт: API сценария, который ядро предоставляет наружу.
interface CalculatorUseCase {
    long add(long a, long b);
}

package m17_hexagonal_ports_adapters.practice.task07;

// Входной (driving) порт: сценарий, который ядро предоставляет наружу.
interface TransferUseCase {
    void transfer(String fromId, String toId, long amount);
}

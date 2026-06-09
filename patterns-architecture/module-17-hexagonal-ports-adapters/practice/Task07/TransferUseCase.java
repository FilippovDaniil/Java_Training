// Входной (driving) порт: сценарий, который ядро предоставляет наружу.
interface TransferUseCase {
    void transfer(String fromId, String toId, long amount);
}

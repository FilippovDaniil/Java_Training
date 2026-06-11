package m17_hexagonal_ports_adapters.practice.task07;

// Driving-адаптер: вызывает ядро через входной порт (в проде — REST-контроллер).
class ConsoleTransferAdapter {
    // TODO: поле final TransferUseCase useCase + конструктор ConsoleTransferAdapter(TransferUseCase useCase)

    public void run(String fromId, String toId, long amount) {
        // TODO: useCase.transfer(fromId, toId, amount);
        //       напечатать "Перевод " + amount + ": " + fromId + "→" + toId
    }
}

package m17_hexagonal_ports_adapters.practice.task03;

// Входной (driving) порт.
interface GreetUseCase {
    String greet(String lang, String name);
}
